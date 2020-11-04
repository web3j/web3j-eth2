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
package org.web3j.eth2.api.schema

/**
 * The [`AttesterSlashing`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/core/0_beacon-chain.md#attesterslashing) object from the Eth2.0 spec.
 * @param attestation1
 * @param attestation2
 */
data class Body2(

    val attestation1: Ethv1beaconpoolattesterSlashingsAttestation1? = null,
    val attestation2: Ethv1beaconpoolattesterSlashingsAttestation1? = null
)
