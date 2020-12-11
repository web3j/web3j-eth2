/*
 * Copyright 2020 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.eth2.api

import mu.KLogging
import org.web3j.eth2.api.schema.BeaconEvent
import org.web3j.eth2.api.schema.BeaconEventType
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.net.URL
import java.util.EnumSet
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import javax.ws.rs.WebApplicationException
import javax.ws.rs.client.WebTarget
import javax.ws.rs.sse.SseEventSource

/**
 * Invocation handler for proxied resources.
 *
 * Handles node events using a Server-Sent Event (SSE) request.
 */
internal class BeaconNodeClientInvocationHandler(
    private val target: WebTarget,
    private val client: Any
) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
        return if (method.isEvent()) {
            logger.trace { "Invoking event method: $method" }
            @Suppress("UNCHECKED_CAST")
            invokeOnEvent(args!![0] as EnumSet<BeaconEventType>, args[1] as Consumer<BeaconEvent>)
        } else {
            logger.trace { "Invoking client method: $method" }
            invokeClient(method, args)
        }
    }

    private fun invokeOnEvent(
        topics: EnumSet<BeaconEventType>,
        onEvent: Consumer<BeaconEvent>
    ): CompletableFuture<Void> {
        val source = SseEventSource.target(clientTarget(topics)).build()
        return SseEventSourceResult(source, onEvent).also {
            it.open()
        }
    }

    private fun invokeClient(method: Method, args: Array<out Any>?): Any? {
        try {
            // Invoke the original method on the client
            return method.invoke(client, *(args ?: arrayOf())).let {
                when {
                    it == null -> null
                    Proxy.isProxyClass(it::class.java) -> {
                        // The result is a Jersey web resource
                        // so we need to wrap it again
                        Proxy.newProxyInstance(
                            method.returnType.classLoader,
                            arrayOf(method.returnType),
                            BeaconNodeClientInvocationHandler(target, it)
                        )
                    }
                    else -> it
                }
            }
        } catch (e: InvocationTargetException) {
            handleException(e, method)
        } catch (e: WebApplicationException) {
            handleException(e, method)
        }
    }

    private fun handleException(error: InvocationTargetException, method: Method): Nothing {
        throw error.targetException.let {
            if (it is WebApplicationException) {
                handleException(it, method)
            } else {
                logger.error {
                    "Unexpected exception while invoking method $method: " +
                            (error.message ?: error::class.java.canonicalName)
                }
                it
            }
        }
    }

    private fun handleException(error: WebApplicationException, method: Method): Nothing {
        logger.error {
            "Client exception while invoking method $method: " +
                    (error.message ?: error.response.statusInfo.reasonPhrase)
        }
        throw error
    }

    private fun clientTarget(topics: EnumSet<BeaconEventType>): WebTarget {
        val resourcePath = client.toString()
            .removePrefix("JerseyWebTarget { ")
            .removeSuffix(" }")
            .run { URL(this).path }
        val topicsParam = topics.joinToString(",") { it.toString() }
        return target.path(resourcePath).queryParam("topics", topicsParam)
    }

    private fun Method.isEvent() = parameterTypes.size == 2 &&
            parameterTypes[0] == EnumSet::class.java &&
            parameterTypes[1] == Consumer::class.java &&
            returnType == CompletableFuture::class.java

    companion object : KLogging()
}
