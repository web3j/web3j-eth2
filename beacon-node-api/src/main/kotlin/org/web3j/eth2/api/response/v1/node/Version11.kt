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
package org.web3j.eth2.api.response.v1.node

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Objects

class Version @JsonCreator constructor(
    @field:Schema(
        description = "A string which uniquely identifies the client implementation and its version; " +
                "similar to [HTTP User-Agent](https://tools.ietf.org/html/rfc7231#section-5.5.3).",
        example = "teku/v0.12.6-dev-994997f8/osx-x86_64/adoptopenjdk-java-11"
    ) @field:JsonProperty("version") @param:JsonProperty("version") val version: String
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val version1 = o as Version
        return version == version1.version
    }

    override fun hashCode(): Int {
        return Objects.hash(version)
    }
}
