# Kotlin client library for Eth2 Beacon Node API

## Requires

* Kotlin 1.3.x
* Gradle 6.x

## Build

## Documentation for API Endpoints

All URIs are relative to *{server_url}*

Tag          | Operation ID  | HTTP request | Description
------------ | ------------- | ------------- | -------------
*Beacon* | [**getBlock**](blob/master/beacon-node-api/src/main/kotlin/org/web3j/eth2/api/beacon/blocks/BlocksResource.kt#L53) | **GET** /eth/v1/beacon/blocks/{block_id} | Get block
*Beacon* | [**getBlockAttestations**](docs/BeaconApi.md#getblockattestations) | **GET** /eth/v1/beacon/blocks/{block_id}/attestations | Get block attestations
*Beacon* | [**getBlockHeader**](docs/BeaconApi.md#getblockheader) | **GET** /eth/v1/beacon/headers/{block_id} | Get block header
*Beacon* | [**getBlockHeaders**](docs/BeaconApi.md#getblockheaders) | **GET** /eth/v1/beacon/headers | Get block headers
*Beacon* | [**getBlockRoot**](docs/BeaconApi.md#getblockroot) | **GET** /eth/v1/beacon/blocks/{block_id}/root | Get block root
*Beacon* | [**getEpochCommittees**](docs/BeaconApi.md#getepochcommittees) | **GET** /eth/v1/beacon/states/{state_id}/committees/{epoch} | Get all committees for epoch
*Beacon* | [**getGenesis**](docs/BeaconApi.md#getgenesis) | **GET** /eth/v1/beacon/genesis | Retrieve details of the chain's genesis.
*Beacon* | [**getPoolAttestations**](docs/BeaconApi.md#getpoolattestations) | **GET** /eth/v1/beacon/pool/attestations | Get Attestations from operations pool
*Beacon* | [**getPoolAttesterSlashings**](docs/BeaconApi.md#getpoolattesterslashings) | **GET** /eth/v1/beacon/pool/attester_slashings | Get AttesterSlashings from operations pool
*Beacon* | [**getPoolProposerSlashings**](docs/BeaconApi.md#getpoolproposerslashings) | **GET** /eth/v1/beacon/pool/proposer_slashings | Get ProposerSlashings from operations pool
*Beacon* | [**getPoolVoluntaryExits**](docs/BeaconApi.md#getpoolvoluntaryexits) | **GET** /eth/v1/beacon/pool/voluntary_exits | Get SignedVoluntaryExit from operations pool
*Beacon* | [**getStateFinalityCheckpoints**](docs/BeaconApi.md#getstatefinalitycheckpoints) | **GET** /eth/v1/beacon/states/{state_id}/finality_checkpoints | Get state finality checkpoints
*Beacon* | [**getStateFork**](docs/BeaconApi.md#getstatefork) | **GET** /eth/v1/beacon/states/{state_id}/fork | Get Fork object for requested state
*Beacon* | [**getStateRoot**](docs/BeaconApi.md#getstateroot) | **GET** /eth/v1/beacon/states/{state_id}/root | Get state SSZ HashTreeRoot
*Beacon* | [**getStateValidator**](docs/BeaconApi.md#getstatevalidator) | **GET** /eth/v1/beacon/states/{state_id}/validators/{validator_id} | Get validator from state by id
*Beacon* | [**getStateValidatorBalances**](docs/BeaconApi.md#getstatevalidatorbalances) | **GET** /eth/v1/beacon/states/{state_id}/validator_balances | Get validator balances from state
*Beacon* | [**getStateValidators**](docs/BeaconApi.md#getstatevalidators) | **GET** /eth/v1/beacon/states/{state_id}/validators | Get validators from state
*Beacon* | [**publishBlock**](docs/BeaconApi.md#publishblock) | **POST** /eth/v1/beacon/blocks | Publish a signed block.
*Beacon* | [**submitPoolAttestations**](docs/BeaconApi.md#submitpoolattestations) | **POST** /eth/v1/beacon/pool/attestations | Submit Attestation object to node
*Beacon* | [**submitPoolAttesterSlashings**](docs/BeaconApi.md#submitpoolattesterslashings) | **POST** /eth/v1/beacon/pool/attester_slashings | Submit AttesterSlashing object to node's pool
*Beacon* | [**submitPoolProposerSlashings**](docs/BeaconApi.md#submitpoolproposerslashings) | **POST** /eth/v1/beacon/pool/proposer_slashings | Submit ProposerSlashing object to node's pool
*Beacon* | [**submitPoolVoluntaryExit**](docs/BeaconApi.md#submitpoolvoluntaryexit) | **POST** /eth/v1/beacon/pool/voluntary_exits | Submit SignedVoluntaryExit object to node's pool
*Config* | [**getDepositContract**](docs/ConfigApi.md#getdepositcontract) | **GET** /eth/v1/config/deposit_contract | Get deposit contract address.
*Config* | [**getForkSchedule**](docs/ConfigApi.md#getforkschedule) | **GET** /eth/v1/config/fork_schedule | Get scheduled upcoming forks.
*Config* | [**getSpec**](docs/ConfigApi.md#getspec) | **GET** /eth/v1/config/spec | Get spec params.
*Debug* | [**getDebugChainHeads**](docs/DebugApi.md#getdebugchainheads) | **GET** /eth/v1/debug/beacon/heads | Get fork choice leaves
*Debug* | [**getState**](docs/DebugApi.md#getstate) | **GET** /eth/v1/debug/beacon/states/{state_id} | Get full BeaconState object
*Events* | [**eventstream**](docs/EventsApi.md#eventstream) | **GET** /eth/v1/events | Subscribe to beacon node events
*Node* | [**getHealth**](docs/NodeApi.md#gethealth) | **GET** /eth/v1/node/health | Get health check
*Node* | [**getNetworkIdentity**](docs/NodeApi.md#getnetworkidentity) | **GET** /eth/v1/node/identity | Get node network identity
*Node* | [**getNodeVersion**](docs/NodeApi.md#getnodeversion) | **GET** /eth/v1/node/version | Get version string of the running beacon node.
*Node* | [**getPeer**](docs/NodeApi.md#getpeer) | **GET** /eth/v1/node/peers/{peer_id} | Get peer
*Node* | [**getPeers**](docs/NodeApi.md#getpeers) | **GET** /eth/v1/node/peers | Get node network peers
*Node* | [**getSyncingStatus**](docs/NodeApi.md#getsyncingstatus) | **GET** /eth/v1/node/syncing | Get node syncing status
*Validator* | [**getAggregatedAttestation**](docs/ValidatorApi.md#getaggregatedattestation) | **GET** /eth/v1/validator/aggregate_attestation | Get aggregated attestation
*Validator* | [**getAttesterDuties**](docs/ValidatorApi.md#getattesterduties) | **POST** /eth/v1/validator/duties/attester/{epoch} | Get attester duties
*Validator* | [**getProposerDuties**](docs/ValidatorApi.md#getproposerduties) | **GET** /eth/v1/validator/duties/proposer/{epoch} | Get block proposers duties
*Validator* | [**prepareBeaconCommitteeSubnet**](docs/ValidatorApi.md#preparebeaconcommitteesubnet) | **POST** /eth/v1/validator/beacon_committee_subscriptions | Signal beacon node to prepare for a committee subnet
*Validator* | [**produceAttestationData**](docs/ValidatorApi.md#produceattestationdata) | **GET** /eth/v1/validator/attestation_data | Produce an attestation data
*Validator* | [**produceBlock**](docs/ValidatorApi.md#produceblock) | **GET** /eth/v1/validator/blocks/{slot} | Produce a new block, without signature.
*Validator* | [**publishAggregateAndProofs**](docs/ValidatorApi.md#publishaggregateandproofs) | **POST** /eth/v1/validator/aggregate_and_proofs | Publish multiple aggregate and proofs
