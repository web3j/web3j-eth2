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

import org.web3j.eth2.api.schema.ErrorMessage
import javax.ws.rs.ProcessingException
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType

/**
 * Beacon Node API exception containing error data.
 *
 * Invoking methods on [org.web3j.eth2.api.BeaconNodeApi] objects may incur on [WebApplicationException]s raised by
 * the remote Beacon Node. To extract the error data returned by the node, you can use the method [of] to map them, eg.:
 * ```
 * try(service = new BeaconNodeService("http://localhost:5051")) {
 *     var client = BeaconNodeClientFactory.build(service);
 *     var response = client.beacon.blocks.findById(NamedBlockId.HEAD);
 *     ...
 * } catch (WebApplicationException exception) {
 *     var error = BeaconNodeError.of(exception);
 *     System.out.println(error.getMessage());
 *     ...
 *     throw error;
 * }
 * ```
 *
 * Alternatively, if the received error data does not correspond with the Beacon Node API
 * [ErrorMessage](https://github.com/ethereum/eth2.0-APIs/blob/master/types/http.yaml#L86)
 * the message will contain the unparsed data from the node.
 */
class BeaconNodeError private constructor(
    val code: Int,
    override val message: String,
    val stacktraces: List<String> = emptyList()
) : RuntimeException(message) {
    companion object {

        @JvmStatic
        fun of(exception: WebApplicationException): BeaconNodeError {
            return with(exception.response) {
                if (hasEntity() && mediaType == MediaType.APPLICATION_JSON_TYPE) {
                    try {
                        // Try to read Beacon node error
                        readEntity(ErrorMessage::class.java).let {
                            BeaconNodeError(
                                code = it.code,
                                message = it.message,
                                stacktraces = it.stacktraces
                            )
                        }
                    } catch (pe: ProcessingException) {
                        // Read server-specific error response
                        BeaconNodeError(
                            code = status,
                            message = readEntity(String::class.java)
                        )
                    }
                } else {
                    BeaconNodeError(
                        code = exception.response.status,
                        message = exception.response.statusInfo.reasonPhrase
                    )
                }
            }
        }
    }
}
