#!/bin/bash
./run-bitcoin.sh
./generate-block-bitcoin.sh
cd code
./gradlew test