# Kotlin client library for Eth2 Beacon Node API

## Requires

* Kotlin 1.3.x
* Gradle 3.3

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in Swagger definitions.

## Documentation for API Endpoints

All URIs are relative to *{server_url}*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*BeaconApi* | [**getBlock**](docs/BeaconApi.md#getblock) | **GET** /eth/v1/beacon/blocks/{block_id} | Get block
*BeaconApi* | [**getBlockAttestations**](docs/BeaconApi.md#getblockattestations) | **GET** /eth/v1/beacon/blocks/{block_id}/attestations | Get block attestations
*BeaconApi* | [**getBlockHeader**](docs/BeaconApi.md#getblockheader) | **GET** /eth/v1/beacon/headers/{block_id} | Get block header
*BeaconApi* | [**getBlockHeaders**](docs/BeaconApi.md#getblockheaders) | **GET** /eth/v1/beacon/headers | Get block headers
*BeaconApi* | [**getBlockRoot**](docs/BeaconApi.md#getblockroot) | **GET** /eth/v1/beacon/blocks/{block_id}/root | Get block root
*BeaconApi* | [**getEpochCommittees**](docs/BeaconApi.md#getepochcommittees) | **GET** /eth/v1/beacon/states/{state_id}/committees/{epoch} | Get all committees for epoch
*BeaconApi* | [**getGenesis**](docs/BeaconApi.md#getgenesis) | **GET** /eth/v1/beacon/genesis | Retrieve details of the chain's genesis.
*BeaconApi* | [**getPoolAttestations**](docs/BeaconApi.md#getpoolattestations) | **GET** /eth/v1/beacon/pool/attestations | Get Attestations from operations pool
*BeaconApi* | [**getPoolAttesterSlashings**](docs/BeaconApi.md#getpoolattesterslashings) | **GET** /eth/v1/beacon/pool/attester_slashings | Get AttesterSlashings from operations pool
*BeaconApi* | [**getPoolProposerSlashings**](docs/BeaconApi.md#getpoolproposerslashings) | **GET** /eth/v1/beacon/pool/proposer_slashings | Get ProposerSlashings from operations pool
*BeaconApi* | [**getPoolVoluntaryExits**](docs/BeaconApi.md#getpoolvoluntaryexits) | **GET** /eth/v1/beacon/pool/voluntary_exits | Get SignedVoluntaryExit from operations pool
*BeaconApi* | [**getStateFinalityCheckpoints**](docs/BeaconApi.md#getstatefinalitycheckpoints) | **GET** /eth/v1/beacon/states/{state_id}/finality_checkpoints | Get state finality checkpoints
*BeaconApi* | [**getStateFork**](docs/BeaconApi.md#getstatefork) | **GET** /eth/v1/beacon/states/{state_id}/fork | Get Fork object for requested state
*BeaconApi* | [**getStateRoot**](docs/BeaconApi.md#getstateroot) | **GET** /eth/v1/beacon/states/{state_id}/root | Get state SSZ HashTreeRoot
*BeaconApi* | [**getStateValidator**](docs/BeaconApi.md#getstatevalidator) | **GET** /eth/v1/beacon/states/{state_id}/validators/{validator_id} | Get validator from state by id
*BeaconApi* | [**getStateValidatorBalances**](docs/BeaconApi.md#getstatevalidatorbalances) | **GET** /eth/v1/beacon/states/{state_id}/validator_balances | Get validator balances from state
*BeaconApi* | [**getStateValidators**](docs/BeaconApi.md#getstatevalidators) | **GET** /eth/v1/beacon/states/{state_id}/validators | Get validators from state
*BeaconApi* | [**publishBlock**](docs/BeaconApi.md#publishblock) | **POST** /eth/v1/beacon/blocks | Publish a signed block.
*BeaconApi* | [**submitPoolAttestations**](docs/BeaconApi.md#submitpoolattestations) | **POST** /eth/v1/beacon/pool/attestations | Submit Attestation object to node
*BeaconApi* | [**submitPoolAttesterSlashings**](docs/BeaconApi.md#submitpoolattesterslashings) | **POST** /eth/v1/beacon/pool/attester_slashings | Submit AttesterSlashing object to node's pool
*BeaconApi* | [**submitPoolProposerSlashings**](docs/BeaconApi.md#submitpoolproposerslashings) | **POST** /eth/v1/beacon/pool/proposer_slashings | Submit ProposerSlashing object to node's pool
*BeaconApi* | [**submitPoolVoluntaryExit**](docs/BeaconApi.md#submitpoolvoluntaryexit) | **POST** /eth/v1/beacon/pool/voluntary_exits | Submit SignedVoluntaryExit object to node's pool
*ConfigApi* | [**getDepositContract**](docs/ConfigApi.md#getdepositcontract) | **GET** /eth/v1/config/deposit_contract | Get deposit contract address.
*ConfigApi* | [**getForkSchedule**](docs/ConfigApi.md#getforkschedule) | **GET** /eth/v1/config/fork_schedule | Get scheduled upcoming forks.
*ConfigApi* | [**getSpec**](docs/ConfigApi.md#getspec) | **GET** /eth/v1/config/spec | Get spec params.
*DebugApi* | [**getDebugChainHeads**](docs/DebugApi.md#getdebugchainheads) | **GET** /eth/v1/debug/beacon/heads | Get fork choice leaves
*DebugApi* | [**getState**](docs/DebugApi.md#getstate) | **GET** /eth/v1/debug/beacon/states/{state_id} | Get full BeaconState object
*EventsApi* | [**eventstream**](docs/EventsApi.md#eventstream) | **GET** /eth/v1/events | Subscribe to beacon node events
*NodeApi* | [**getHealth**](docs/NodeApi.md#gethealth) | **GET** /eth/v1/node/health | Get health check
*NodeApi* | [**getNetworkIdentity**](docs/NodeApi.md#getnetworkidentity) | **GET** /eth/v1/node/identity | Get node network identity
*NodeApi* | [**getNodeVersion**](docs/NodeApi.md#getnodeversion) | **GET** /eth/v1/node/version | Get version string of the running beacon node.
*NodeApi* | [**getPeer**](docs/NodeApi.md#getpeer) | **GET** /eth/v1/node/peers/{peer_id} | Get peer
*NodeApi* | [**getPeers**](docs/NodeApi.md#getpeers) | **GET** /eth/v1/node/peers | Get node network peers
*NodeApi* | [**getSyncingStatus**](docs/NodeApi.md#getsyncingstatus) | **GET** /eth/v1/node/syncing | Get node syncing status
*ValidatorApi* | [**getAggregatedAttestation**](docs/ValidatorApi.md#getaggregatedattestation) | **GET** /eth/v1/validator/aggregate_attestation | Get aggregated attestation
*ValidatorApi* | [**getAttesterDuties**](docs/ValidatorApi.md#getattesterduties) | **POST** /eth/v1/validator/duties/attester/{epoch} | Get attester duties
*ValidatorApi* | [**getProposerDuties**](docs/ValidatorApi.md#getproposerduties) | **GET** /eth/v1/validator/duties/proposer/{epoch} | Get block proposers duties
*ValidatorApi* | [**prepareBeaconCommitteeSubnet**](docs/ValidatorApi.md#preparebeaconcommitteesubnet) | **POST** /eth/v1/validator/beacon_committee_subscriptions | Signal beacon node to prepare for a committee subnet
*ValidatorApi* | [**produceAttestationData**](docs/ValidatorApi.md#produceattestationdata) | **GET** /eth/v1/validator/attestation_data | Produce an attestation data
*ValidatorApi* | [**produceBlock**](docs/ValidatorApi.md#produceblock) | **GET** /eth/v1/validator/blocks/{slot} | Produce a new block, without signature.
*ValidatorApi* | [**publishAggregateAndProofs**](docs/ValidatorApi.md#publishaggregateandproofs) | **POST** /eth/v1/validator/aggregate_and_proofs | Publish multiple aggregate and proofs
*ValidatorRequiredApiApi* | [**eventstream**](docs/ValidatorRequiredApiApi.md#eventstream) | **GET** /eth/v1/events | Subscribe to beacon node events
*ValidatorRequiredApiApi* | [**getAggregatedAttestation**](docs/ValidatorRequiredApiApi.md#getaggregatedattestation) | **GET** /eth/v1/validator/aggregate_attestation | Get aggregated attestation
*ValidatorRequiredApiApi* | [**getAttesterDuties**](docs/ValidatorRequiredApiApi.md#getattesterduties) | **POST** /eth/v1/validator/duties/attester/{epoch} | Get attester duties
*ValidatorRequiredApiApi* | [**getGenesis**](docs/ValidatorRequiredApiApi.md#getgenesis) | **GET** /eth/v1/beacon/genesis | Retrieve details of the chain's genesis.
*ValidatorRequiredApiApi* | [**getProposerDuties**](docs/ValidatorRequiredApiApi.md#getproposerduties) | **GET** /eth/v1/validator/duties/proposer/{epoch} | Get block proposers duties
*ValidatorRequiredApiApi* | [**getSpec**](docs/ValidatorRequiredApiApi.md#getspec) | **GET** /eth/v1/config/spec | Get spec params.
*ValidatorRequiredApiApi* | [**getStateFork**](docs/ValidatorRequiredApiApi.md#getstatefork) | **GET** /eth/v1/beacon/states/{state_id}/fork | Get Fork object for requested state
*ValidatorRequiredApiApi* | [**getStateValidator**](docs/ValidatorRequiredApiApi.md#getstatevalidator) | **GET** /eth/v1/beacon/states/{state_id}/validators/{validator_id} | Get validator from state by id
*ValidatorRequiredApiApi* | [**getSyncingStatus**](docs/ValidatorRequiredApiApi.md#getsyncingstatus) | **GET** /eth/v1/node/syncing | Get node syncing status
*ValidatorRequiredApiApi* | [**prepareBeaconCommitteeSubnet**](docs/ValidatorRequiredApiApi.md#preparebeaconcommitteesubnet) | **POST** /eth/v1/validator/beacon_committee_subscriptions | Signal beacon node to prepare for a committee subnet
*ValidatorRequiredApiApi* | [**produceAttestationData**](docs/ValidatorRequiredApiApi.md#produceattestationdata) | **GET** /eth/v1/validator/attestation_data | Produce an attestation data
*ValidatorRequiredApiApi* | [**produceBlock**](docs/ValidatorRequiredApiApi.md#produceblock) | **GET** /eth/v1/validator/blocks/{slot} | Produce a new block, without signature.
*ValidatorRequiredApiApi* | [**publishAggregateAndProofs**](docs/ValidatorRequiredApiApi.md#publishaggregateandproofs) | **POST** /eth/v1/validator/aggregate_and_proofs | Publish multiple aggregate and proofs
*ValidatorRequiredApiApi* | [**publishBlock**](docs/ValidatorRequiredApiApi.md#publishblock) | **POST** /eth/v1/beacon/blocks | Publish a signed block.
*ValidatorRequiredApiApi* | [**submitPoolAttestations**](docs/ValidatorRequiredApiApi.md#submitpoolattestations) | **POST** /eth/v1/beacon/pool/attestations | Submit Attestation object to node


