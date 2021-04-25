#!/bin/bash
./run-bitcoin.sh
./generate-block-bitcoin.sh
cd code
./gradlew test
rm -rf /workdir/bitcoin_dir/regtest