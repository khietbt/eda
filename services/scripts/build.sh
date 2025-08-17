#!/usr/bin/bash

SCRIPT=$(readlink -f -- "$0")
SCRIPT_DIR=$(dirname "${SCRIPT}")
SERVICE_DIR=$(dirname "${SCRIPT_DIR}")

pushd $(pwd)

cd ${SERVICE_DIR} && mvn clean package

popd
