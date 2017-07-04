#!/usr/bin/env bash
set -ex
. conf.properties

rm -rf hazelcast
git clone $hz_repository
cd hazelcast
git checkout $commit_sha
mvn clean compile install -DskipTests
cp hazelcast-all/target/hazelcast-all*.jar ../
cd ..
rm hazelcast-all*-sources.jar
