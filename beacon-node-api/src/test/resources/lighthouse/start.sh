#!/bin/bash
# https://github.com/sigp/lighthouse/tree/master/scripts/local_testnet
./setup.sh
./beacon_node.sh
./validator_client.sh
./second_beacon_node.sh
