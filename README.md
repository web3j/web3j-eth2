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
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in Swagger definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
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

<a name="documentation-for-models"></a>
## Documentation for Models

 - [io.swagger.client.models.AllOfAttestationDataBeaconBlockRoot](docs/AllOfAttestationDataBeaconBlockRoot.md)
 - [io.swagger.client.models.AllOfAttestationSignature](docs/AllOfAttestationSignature.md)
 - [io.swagger.client.models.AllOfAttesterDutyCommitteeIndex](docs/AllOfAttesterDutyCommitteeIndex.md)
 - [io.swagger.client.models.AllOfAttesterDutyCommitteeLength](docs/AllOfAttesterDutyCommitteeLength.md)
 - [io.swagger.client.models.AllOfAttesterDutyCommitteesAtSlot](docs/AllOfAttesterDutyCommitteesAtSlot.md)
 - [io.swagger.client.models.AllOfAttesterDutySlot](docs/AllOfAttesterDutySlot.md)
 - [io.swagger.client.models.AllOfAttesterDutyValidatorCommitteeIndex](docs/AllOfAttesterDutyValidatorCommitteeIndex.md)
 - [io.swagger.client.models.AllOfAttesterDutyValidatorIndex](docs/AllOfAttesterDutyValidatorIndex.md)
 - [io.swagger.client.models.AllOfBeaconBlockBodyDataAmount](docs/AllOfBeaconBlockBodyDataAmount.md)
 - [io.swagger.client.models.AllOfBeaconBlockBodyDataSignature](docs/AllOfBeaconBlockBodyDataSignature.md)
 - [io.swagger.client.models.AllOfBeaconBlockBodyDataWithdrawalCredentials](docs/AllOfBeaconBlockBodyDataWithdrawalCredentials.md)
 - [io.swagger.client.models.AllOfBeaconBlockBodyDepositsProofItems](docs/AllOfBeaconBlockBodyDepositsProofItems.md)
 - [io.swagger.client.models.AllOfBeaconBlockBodyRandaoReveal](docs/AllOfBeaconBlockBodyRandaoReveal.md)
 - [io.swagger.client.models.AllOfBeaconStateBalancesItems](docs/AllOfBeaconStateBalancesItems.md)
 - [io.swagger.client.models.AllOfBeaconStateBlockRootsItems](docs/AllOfBeaconStateBlockRootsItems.md)
 - [io.swagger.client.models.AllOfBeaconStateCurrentEpochAttestationsItems](docs/AllOfBeaconStateCurrentEpochAttestationsItems.md)
 - [io.swagger.client.models.AllOfBeaconStateEth1DataBlockHash](docs/AllOfBeaconStateEth1DataBlockHash.md)
 - [io.swagger.client.models.AllOfBeaconStateEth1DataDepositCount](docs/AllOfBeaconStateEth1DataDepositCount.md)
 - [io.swagger.client.models.AllOfBeaconStateEth1DataDepositRoot](docs/AllOfBeaconStateEth1DataDepositRoot.md)
 - [io.swagger.client.models.AllOfBeaconStateEth1DataVotesItems](docs/AllOfBeaconStateEth1DataVotesItems.md)
 - [io.swagger.client.models.AllOfBeaconStateHistoricalRootsItems](docs/AllOfBeaconStateHistoricalRootsItems.md)
 - [io.swagger.client.models.AllOfBeaconStateLatestBlockHeader](docs/AllOfBeaconStateLatestBlockHeader.md)
 - [io.swagger.client.models.AllOfBeaconStatePreviousEpochAttestationsItems](docs/AllOfBeaconStatePreviousEpochAttestationsItems.md)
 - [io.swagger.client.models.AllOfBeaconStateRandaoMixesItems](docs/AllOfBeaconStateRandaoMixesItems.md)
 - [io.swagger.client.models.AllOfBeaconStateSlashingsItems](docs/AllOfBeaconStateSlashingsItems.md)
 - [io.swagger.client.models.AllOfBeaconStateStateRootsItems](docs/AllOfBeaconStateStateRootsItems.md)
 - [io.swagger.client.models.AllOfBeaconStateValidatorsItems](docs/AllOfBeaconStateValidatorsItems.md)
 - [io.swagger.client.models.AllOfCommitteeIndex](docs/AllOfCommitteeIndex.md)
 - [io.swagger.client.models.AllOfGetAttesterDutiesResponseDataCommitteeIndex](docs/AllOfGetAttesterDutiesResponseDataCommitteeIndex.md)
 - [io.swagger.client.models.AllOfGetAttesterDutiesResponseDataCommitteeLength](docs/AllOfGetAttesterDutiesResponseDataCommitteeLength.md)
 - [io.swagger.client.models.AllOfGetAttesterDutiesResponseDataCommitteesAtSlot](docs/AllOfGetAttesterDutiesResponseDataCommitteesAtSlot.md)
 - [io.swagger.client.models.AllOfGetAttesterDutiesResponseDataSlot](docs/AllOfGetAttesterDutiesResponseDataSlot.md)
 - [io.swagger.client.models.AllOfGetAttesterDutiesResponseDataValidatorCommitteeIndex](docs/AllOfGetAttesterDutiesResponseDataValidatorCommitteeIndex.md)
 - [io.swagger.client.models.AllOfGetAttesterDutiesResponseDataValidatorIndex](docs/AllOfGetAttesterDutiesResponseDataValidatorIndex.md)
 - [io.swagger.client.models.AllOfGetBlockRootResponseDataRoot](docs/AllOfGetBlockRootResponseDataRoot.md)
 - [io.swagger.client.models.AllOfGetDepositContractResponseDataAddress](docs/AllOfGetDepositContractResponseDataAddress.md)
 - [io.swagger.client.models.AllOfGetEpochCommitteesResponseDataIndex](docs/AllOfGetEpochCommitteesResponseDataIndex.md)
 - [io.swagger.client.models.AllOfGetGenesisResponseDataGenesisTime](docs/AllOfGetGenesisResponseDataGenesisTime.md)
 - [io.swagger.client.models.AllOfGetNetworkIdentityResponseDataDiscoveryAddressesItems](docs/AllOfGetNetworkIdentityResponseDataDiscoveryAddressesItems.md)
 - [io.swagger.client.models.AllOfGetNetworkIdentityResponseDataP2pAddressesItems](docs/AllOfGetNetworkIdentityResponseDataP2pAddressesItems.md)
 - [io.swagger.client.models.AllOfGetPeersResponseDataEnr](docs/AllOfGetPeersResponseDataEnr.md)
 - [io.swagger.client.models.AllOfGetProposerDutiesResponseDataSlot](docs/AllOfGetProposerDutiesResponseDataSlot.md)
 - [io.swagger.client.models.AllOfGetProposerDutiesResponseDataValidatorIndex](docs/AllOfGetProposerDutiesResponseDataValidatorIndex.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataBalancesItems](docs/AllOfGetStateResponseDataBalancesItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataBlockRootsItems](docs/AllOfGetStateResponseDataBlockRootsItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataCurrentEpochAttestationsItems](docs/AllOfGetStateResponseDataCurrentEpochAttestationsItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataEth1DataVotesItems](docs/AllOfGetStateResponseDataEth1DataVotesItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataHistoricalRootsItems](docs/AllOfGetStateResponseDataHistoricalRootsItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataLatestBlockHeader](docs/AllOfGetStateResponseDataLatestBlockHeader.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataPreviousEpochAttestationsItems](docs/AllOfGetStateResponseDataPreviousEpochAttestationsItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataRandaoMixesItems](docs/AllOfGetStateResponseDataRandaoMixesItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataSlashingsItems](docs/AllOfGetStateResponseDataSlashingsItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataStateRootsItems](docs/AllOfGetStateResponseDataStateRootsItems.md)
 - [io.swagger.client.models.AllOfGetStateResponseDataValidatorsItems](docs/AllOfGetStateResponseDataValidatorsItems.md)
 - [io.swagger.client.models.AllOfGetStateRootResponseDataRoot](docs/AllOfGetStateRootResponseDataRoot.md)
 - [io.swagger.client.models.AllOfGetStateValidatorBalancesResponseDataBalance](docs/AllOfGetStateValidatorBalancesResponseDataBalance.md)
 - [io.swagger.client.models.AllOfGetStateValidatorBalancesResponseDataIndex](docs/AllOfGetStateValidatorBalancesResponseDataIndex.md)
 - [io.swagger.client.models.AllOfGetStateValidatorsResponseDataBalance](docs/AllOfGetStateValidatorsResponseDataBalance.md)
 - [io.swagger.client.models.AllOfGetStateValidatorsResponseDataIndex](docs/AllOfGetStateValidatorsResponseDataIndex.md)
 - [io.swagger.client.models.AllOfGetSyncingStatusResponseDataHeadSlot](docs/AllOfGetSyncingStatusResponseDataHeadSlot.md)
 - [io.swagger.client.models.AllOfGetSyncingStatusResponseDataSyncDistance](docs/AllOfGetSyncingStatusResponseDataSyncDistance.md)
 - [io.swagger.client.models.AllOfNetworkIdentityDiscoveryAddressesItems](docs/AllOfNetworkIdentityDiscoveryAddressesItems.md)
 - [io.swagger.client.models.AllOfNetworkIdentityMetadataAttnets](docs/AllOfNetworkIdentityMetadataAttnets.md)
 - [io.swagger.client.models.AllOfNetworkIdentityMetadataSeqNumber](docs/AllOfNetworkIdentityMetadataSeqNumber.md)
 - [io.swagger.client.models.AllOfNetworkIdentityP2pAddressesItems](docs/AllOfNetworkIdentityP2pAddressesItems.md)
 - [io.swagger.client.models.AllOfPeerEnr](docs/AllOfPeerEnr.md)
 - [io.swagger.client.models.AllOfProduceBlockResponseBacktickdataBacktick](docs/AllOfProduceBlockResponseBacktickdataBacktick.md)
 - [io.swagger.client.models.AllOfProposerDutySlot](docs/AllOfProposerDutySlot.md)
 - [io.swagger.client.models.AllOfProposerDutyValidatorIndex](docs/AllOfProposerDutyValidatorIndex.md)
 - [io.swagger.client.models.AllOfSignedAggregateAndProofMessage](docs/AllOfSignedAggregateAndProofMessage.md)
 - [io.swagger.client.models.AllOfSignedBeaconBlockHeaderMessage](docs/AllOfSignedBeaconBlockHeaderMessage.md)
 - [io.swagger.client.models.AllOfSignedBeaconBlockMessage](docs/AllOfSignedBeaconBlockMessage.md)
 - [io.swagger.client.models.AllOfValidatorBalanceResponseBalance](docs/AllOfValidatorBalanceResponseBalance.md)
 - [io.swagger.client.models.AllOfValidatorBalanceResponseIndex](docs/AllOfValidatorBalanceResponseIndex.md)
 - [io.swagger.client.models.AllOfValidatorResponseBalance](docs/AllOfValidatorResponseBalance.md)
 - [io.swagger.client.models.AllOfValidatorResponseIndex](docs/AllOfValidatorResponseIndex.md)
 - [io.swagger.client.models.AllOfValidatorResponseValidatorActivationEligibilityEpoch](docs/AllOfValidatorResponseValidatorActivationEligibilityEpoch.md)
 - [io.swagger.client.models.AllOfValidatorResponseValidatorActivationEpoch](docs/AllOfValidatorResponseValidatorActivationEpoch.md)
 - [io.swagger.client.models.AllOfValidatorResponseValidatorEffectiveBalance](docs/AllOfValidatorResponseValidatorEffectiveBalance.md)
 - [io.swagger.client.models.AllOfValidatorResponseValidatorExitEpoch](docs/AllOfValidatorResponseValidatorExitEpoch.md)
 - [io.swagger.client.models.AllOfValidatorResponseValidatorWithdrawableEpoch](docs/AllOfValidatorResponseValidatorWithdrawableEpoch.md)
 - [io.swagger.client.models.AllOfValidatorResponseValidatorWithdrawalCredentials](docs/AllOfValidatorResponseValidatorWithdrawalCredentials.md)
 - [io.swagger.client.models.AllOfbody1Signature](docs/AllOfbody1Signature.md)
 - [io.swagger.client.models.AllOfbody5Message](docs/AllOfbody5Message.md)
 - [io.swagger.client.models.AllOfbody6CommitteesAtSlot](docs/AllOfbody6CommitteesAtSlot.md)
 - [io.swagger.client.models.AllOfbody6Slot](docs/AllOfbody6Slot.md)
 - [io.swagger.client.models.AllOfbodyMessage](docs/AllOfbodyMessage.md)
 - [io.swagger.client.models.AllOfethv1beaconpoolattestationsDataBeaconBlockRoot](docs/AllOfethv1beaconpoolattestationsDataBeaconBlockRoot.md)
 - [io.swagger.client.models.AllOfethv1beaconpoolattesterSlashingsAttestation1Signature](docs/AllOfethv1beaconpoolattesterSlashingsAttestation1Signature.md)
 - [io.swagger.client.models.AllOfethv1beaconpoolproposerSlashingsSignedHeader1Message](docs/AllOfethv1beaconpoolproposerSlashingsSignedHeader1Message.md)
 - [io.swagger.client.models.AllOfethv1beaconpoolvoluntaryExitsMessageEpoch](docs/AllOfethv1beaconpoolvoluntaryExitsMessageEpoch.md)
 - [io.swagger.client.models.AllOfethv1beaconpoolvoluntaryExitsMessageValidatorIndex](docs/AllOfethv1beaconpoolvoluntaryExitsMessageValidatorIndex.md)
 - [io.swagger.client.models.Attestation](docs/Attestation.md)
 - [io.swagger.client.models.AttestationData](docs/AttestationData.md)
 - [io.swagger.client.models.AttesterDuty](docs/AttesterDuty.md)
 - [io.swagger.client.models.AttesterSlashing](docs/AttesterSlashing.md)
 - [io.swagger.client.models.BeaconBlock](docs/BeaconBlock.md)
 - [io.swagger.client.models.BeaconBlockBody](docs/BeaconBlockBody.md)
 - [io.swagger.client.models.BeaconBlockBodyData](docs/BeaconBlockBodyData.md)
 - [io.swagger.client.models.BeaconBlockBodyDeposits](docs/BeaconBlockBodyDeposits.md)
 - [io.swagger.client.models.BeaconState](docs/BeaconState.md)
 - [io.swagger.client.models.BeaconStateEth1Data](docs/BeaconStateEth1Data.md)
 - [io.swagger.client.models.BeaconStateFork](docs/BeaconStateFork.md)
 - [io.swagger.client.models.Body](docs/Body.md)
 - [io.swagger.client.models.Body1](docs/Body1.md)
 - [io.swagger.client.models.Body2](docs/Body2.md)
 - [io.swagger.client.models.Body3](docs/Body3.md)
 - [io.swagger.client.models.Body4](docs/Body4.md)
 - [io.swagger.client.models.Body5](docs/Body5.md)
 - [io.swagger.client.models.Body6](docs/Body6.md)
 - [io.swagger.client.models.Checkpoint](docs/Checkpoint.md)
 - [io.swagger.client.models.Committee](docs/Committee.md)
 - [io.swagger.client.models.Epoch](docs/Epoch.md)
 - [io.swagger.client.models.ErrorMessage](docs/ErrorMessage.md)
 - [io.swagger.client.models.Ethv1beaconpoolattestationsData](docs/Ethv1beaconpoolattestationsData.md)
 - [io.swagger.client.models.Ethv1beaconpoolattestationsDataSource](docs/Ethv1beaconpoolattestationsDataSource.md)
 - [io.swagger.client.models.Ethv1beaconpoolattesterSlashingsAttestation1](docs/Ethv1beaconpoolattesterSlashingsAttestation1.md)
 - [io.swagger.client.models.Ethv1beaconpoolproposerSlashingsSignedHeader1](docs/Ethv1beaconpoolproposerSlashingsSignedHeader1.md)
 - [io.swagger.client.models.Ethv1beaconpoolvoluntaryExitsMessage](docs/Ethv1beaconpoolvoluntaryExitsMessage.md)
 - [io.swagger.client.models.Fork](docs/Fork.md)
 - [io.swagger.client.models.ForkVersion](docs/ForkVersion.md)
 - [io.swagger.client.models.GenesisTime](docs/GenesisTime.md)
 - [io.swagger.client.models.GetAggregatedAttestationResponse](docs/GetAggregatedAttestationResponse.md)
 - [io.swagger.client.models.GetAttesterDutiesResponse](docs/GetAttesterDutiesResponse.md)
 - [io.swagger.client.models.GetAttesterDutiesResponseData](docs/GetAttesterDutiesResponseData.md)
 - [io.swagger.client.models.GetBlockAttestationsResponse](docs/GetBlockAttestationsResponse.md)
 - [io.swagger.client.models.GetBlockHeaderResponse](docs/GetBlockHeaderResponse.md)
 - [io.swagger.client.models.GetBlockHeadersResponse](docs/GetBlockHeadersResponse.md)
 - [io.swagger.client.models.GetBlockHeadersResponseData](docs/GetBlockHeadersResponseData.md)
 - [io.swagger.client.models.GetBlockResponse](docs/GetBlockResponse.md)
 - [io.swagger.client.models.GetBlockRootResponse](docs/GetBlockRootResponse.md)
 - [io.swagger.client.models.GetBlockRootResponseData](docs/GetBlockRootResponseData.md)
 - [io.swagger.client.models.GetDebugChainHeadsResponse](docs/GetDebugChainHeadsResponse.md)
 - [io.swagger.client.models.GetDebugChainHeadsResponseData](docs/GetDebugChainHeadsResponseData.md)
 - [io.swagger.client.models.GetDepositContractResponse](docs/GetDepositContractResponse.md)
 - [io.swagger.client.models.GetDepositContractResponseData](docs/GetDepositContractResponseData.md)
 - [io.swagger.client.models.GetEpochCommitteesResponse](docs/GetEpochCommitteesResponse.md)
 - [io.swagger.client.models.GetEpochCommitteesResponseData](docs/GetEpochCommitteesResponseData.md)
 - [io.swagger.client.models.GetForkScheduleResponse](docs/GetForkScheduleResponse.md)
 - [io.swagger.client.models.GetGenesisResponse](docs/GetGenesisResponse.md)
 - [io.swagger.client.models.GetGenesisResponseData](docs/GetGenesisResponseData.md)
 - [io.swagger.client.models.GetNetworkIdentityResponse](docs/GetNetworkIdentityResponse.md)
 - [io.swagger.client.models.GetNetworkIdentityResponseData](docs/GetNetworkIdentityResponseData.md)
 - [io.swagger.client.models.GetPeerResponse](docs/GetPeerResponse.md)
 - [io.swagger.client.models.GetPeersResponse](docs/GetPeersResponse.md)
 - [io.swagger.client.models.GetPeersResponseData](docs/GetPeersResponseData.md)
 - [io.swagger.client.models.GetPoolAttestationsResponse](docs/GetPoolAttestationsResponse.md)
 - [io.swagger.client.models.GetPoolAttesterSlashingsResponse](docs/GetPoolAttesterSlashingsResponse.md)
 - [io.swagger.client.models.GetPoolProposerSlashingsResponse](docs/GetPoolProposerSlashingsResponse.md)
 - [io.swagger.client.models.GetPoolVoluntaryExitsResponse](docs/GetPoolVoluntaryExitsResponse.md)
 - [io.swagger.client.models.GetProposerDutiesResponse](docs/GetProposerDutiesResponse.md)
 - [io.swagger.client.models.GetProposerDutiesResponseData](docs/GetProposerDutiesResponseData.md)
 - [io.swagger.client.models.GetSpecResponse](docs/GetSpecResponse.md)
 - [io.swagger.client.models.GetStateFinalityCheckpointsResponse](docs/GetStateFinalityCheckpointsResponse.md)
 - [io.swagger.client.models.GetStateFinalityCheckpointsResponseData](docs/GetStateFinalityCheckpointsResponseData.md)
 - [io.swagger.client.models.GetStateForkResponse](docs/GetStateForkResponse.md)
 - [io.swagger.client.models.GetStateResponse](docs/GetStateResponse.md)
 - [io.swagger.client.models.GetStateResponseData](docs/GetStateResponseData.md)
 - [io.swagger.client.models.GetStateRootResponse](docs/GetStateRootResponse.md)
 - [io.swagger.client.models.GetStateRootResponseData](docs/GetStateRootResponseData.md)
 - [io.swagger.client.models.GetStateValidatorBalancesResponse](docs/GetStateValidatorBalancesResponse.md)
 - [io.swagger.client.models.GetStateValidatorBalancesResponseData](docs/GetStateValidatorBalancesResponseData.md)
 - [io.swagger.client.models.GetStateValidatorResponse](docs/GetStateValidatorResponse.md)
 - [io.swagger.client.models.GetStateValidatorsResponse](docs/GetStateValidatorsResponse.md)
 - [io.swagger.client.models.GetStateValidatorsResponseData](docs/GetStateValidatorsResponseData.md)
 - [io.swagger.client.models.GetSyncingStatusResponse](docs/GetSyncingStatusResponse.md)
 - [io.swagger.client.models.GetSyncingStatusResponseData](docs/GetSyncingStatusResponseData.md)
 - [io.swagger.client.models.GetVersionResponse](docs/GetVersionResponse.md)
 - [io.swagger.client.models.GetVersionResponseData](docs/GetVersionResponseData.md)
 - [io.swagger.client.models.Graffiti](docs/Graffiti.md)
 - [io.swagger.client.models.Hex](docs/Hex.md)
 - [io.swagger.client.models.Index](docs/Index.md)
 - [io.swagger.client.models.InlineResponse400](docs/InlineResponse400.md)
 - [io.swagger.client.models.InlineResponse4001](docs/InlineResponse4001.md)
 - [io.swagger.client.models.InlineResponse40010](docs/InlineResponse40010.md)
 - [io.swagger.client.models.InlineResponse40011](docs/InlineResponse40011.md)
 - [io.swagger.client.models.InlineResponse40012](docs/InlineResponse40012.md)
 - [io.swagger.client.models.InlineResponse4002](docs/InlineResponse4002.md)
 - [io.swagger.client.models.InlineResponse4003](docs/InlineResponse4003.md)
 - [io.swagger.client.models.InlineResponse4004](docs/InlineResponse4004.md)
 - [io.swagger.client.models.InlineResponse4005](docs/InlineResponse4005.md)
 - [io.swagger.client.models.InlineResponse4006](docs/InlineResponse4006.md)
 - [io.swagger.client.models.InlineResponse4007](docs/InlineResponse4007.md)
 - [io.swagger.client.models.InlineResponse4008](docs/InlineResponse4008.md)
 - [io.swagger.client.models.InlineResponse4009](docs/InlineResponse4009.md)
 - [io.swagger.client.models.InlineResponse404](docs/InlineResponse404.md)
 - [io.swagger.client.models.InlineResponse4041](docs/InlineResponse4041.md)
 - [io.swagger.client.models.InlineResponse4042](docs/InlineResponse4042.md)
 - [io.swagger.client.models.InlineResponse4043](docs/InlineResponse4043.md)
 - [io.swagger.client.models.InlineResponse4044](docs/InlineResponse4044.md)
 - [io.swagger.client.models.InlineResponse500](docs/InlineResponse500.md)
 - [io.swagger.client.models.NetworkIdentity](docs/NetworkIdentity.md)
 - [io.swagger.client.models.NetworkIdentityMetadata](docs/NetworkIdentityMetadata.md)
 - [io.swagger.client.models.ParentRoot](docs/ParentRoot.md)
 - [io.swagger.client.models.Peer](docs/Peer.md)
 - [io.swagger.client.models.PeerId](docs/PeerId.md)
 - [io.swagger.client.models.ProduceAttestationDataResponse](docs/ProduceAttestationDataResponse.md)
 - [io.swagger.client.models.ProduceBlockResponse](docs/ProduceBlockResponse.md)
 - [io.swagger.client.models.ProposerDuty](docs/ProposerDuty.md)
 - [io.swagger.client.models.ProposerSlashing](docs/ProposerSlashing.md)
 - [io.swagger.client.models.Root](docs/Root.md)
 - [io.swagger.client.models.Signature](docs/Signature.md)
 - [io.swagger.client.models.SignedAggregateAndProof](docs/SignedAggregateAndProof.md)
 - [io.swagger.client.models.SignedBeaconBlock](docs/SignedBeaconBlock.md)
 - [io.swagger.client.models.SignedBeaconBlockHeader](docs/SignedBeaconBlockHeader.md)
 - [io.swagger.client.models.SignedVoluntaryExit](docs/SignedVoluntaryExit.md)
 - [io.swagger.client.models.Slot](docs/Slot.md)
 - [io.swagger.client.models.Slot1](docs/Slot1.md)
 - [io.swagger.client.models.Uint64](docs/Uint64.md)
 - [io.swagger.client.models.ValidatorBalanceResponse](docs/ValidatorBalanceResponse.md)
 - [io.swagger.client.models.ValidatorResponse](docs/ValidatorResponse.md)
 - [io.swagger.client.models.ValidatorResponseValidator](docs/ValidatorResponseValidator.md)
 - [io.swagger.client.models.ValidatorStatus](docs/ValidatorStatus.md)
 - [io.swagger.client.models.Version](docs/Version.md)

<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
