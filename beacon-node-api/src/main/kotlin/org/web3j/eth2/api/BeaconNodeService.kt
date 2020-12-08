/*
 * Copyright 2019 Web3 Labs Ltd.
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

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.ClientProperties
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.cfg.Annotations
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider
import org.glassfish.jersey.logging.LoggingFeature
import org.slf4j.bridge.SLF4JBridgeHandler
import org.web3j.eth2.api.BeaconNodeService.Companion.DEFAULT_CONNECT_TIMEOUT
import org.web3j.eth2.api.BeaconNodeService.Companion.DEFAULT_READ_TIMEOUT
import java.util.logging.Level
import java.util.logging.Logger
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder

/**
 * The Beacon Node API service required to build [org.web3j.eth2.api.BeaconNodeApi] clients.
 *
 * This class is auto-closeable, so it will release automatically any resources on objects managed by the
 * try-with-resources statement, eg.:
 * ```
 * try(service = new BeaconNodeService("http://localhost:5051")) {
 *     var client = BeaconNodeClientFactory.build(service);
 *     var response = client.beacon.blocks.findById(NamedBlockId.HEAD);
 *     ...
 * } catch (WebApplicationException exception) {
 *     ...
 * }
 * ```
 *
 * @see [BeaconNodeClientFactory]
 */
class BeaconNodeService @JvmOverloads constructor(

    /**
     * Beacon Node URI (e.g. `http://public-mainnet-node.ethereum.org/api`).
     */
    val uri: String,

    /**
     * Read timeout interval, in milliseconds.
     *
     * A value of zero (0) is equivalent to an interval of infinity.
     *
     * The default value is [DEFAULT_READ_TIMEOUT].
     */
    readTimeout: Int = DEFAULT_READ_TIMEOUT,

    /**
     * Connect timeout interval, in milliseconds.
     *
     * A value of zero (0) is equivalent to an interval of infinity.
     *
     * The default value is [DEFAULT_CONNECT_TIMEOUT].
     */
    connectTimeout: Int = DEFAULT_CONNECT_TIMEOUT
) : AutoCloseable {

    private val mapper = jacksonObjectMapper()
        .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
        .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)

    internal val client: Client by lazy {
        val config = ClientConfig().apply {
            // Redirect ALL logs to SLF4J using logging.properties
            register(LoggingFeature(logger.apply { level = Level.ALL }, Short.MAX_VALUE.toInt()))
            register(JacksonJaxbJsonProvider(mapper, arrayOf(Annotations.JACKSON)))
            property(ClientProperties.READ_TIMEOUT, readTimeout)
            property(ClientProperties.CONNECT_TIMEOUT, connectTimeout)
        }
        ClientBuilder.newClient(config)
    }

    /**
     * Closes this service by releasing internal resources.
     *
     * Calling this method effectively invalidates all [org.web3j.eth2.api.BeaconNodeApi] resource targets.
     * Invoking any method on such targets once the client is closed would result in an [IllegalStateException]
     * being thrown.
     */
    override fun close() = client.close()

    companion object {
        const val DEFAULT_READ_TIMEOUT: Int = 60000
        const val DEFAULT_CONNECT_TIMEOUT: Int = 60000

        init {
            SLF4JBridgeHandler.removeHandlersForRootLogger()
            SLF4JBridgeHandler.install()
        }

        private val logger = Logger.getLogger(BeaconNodeService::class.java.canonicalName)!!
    }
}
