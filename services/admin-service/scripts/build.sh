#!/usr/bin/bash

TOPDIR=$(dirname "$(dirname -- "$(readlink -f -- "$0")")")

cd ${TOPDIR}

rm -rf target

mvn clean package && docker compose up -d --build
