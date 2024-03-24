#!/bin/bash

stage=$1
runTests=$2
identityFile=$3

buildCommand="./gradlew clean build"

if [ "${runTests}" = false ]; then
  buildCommand="${buildCommand} -x check"
fi

echo "* Building jar file ..."
if ! eval "$buildCommand"; then
  exit 1
fi

host=ubuntu@hostname."${stage}".domain-name.com
echo "* Host = ${host}"

jarDir='build/libs'
files=(build/libs/*)
jarPath=${files[0]}
jarName=($(ls ${jarDir}))

sshStopAndRemoveOldJar="-o StrictHostKeyChecking=no -T -p 2426 ${host} \
  <<EOF
  cd
  sudo killall -9 java
  killall screen
  rm -f *.jar
  rm -f .env
EOF"

scpJar="-o StrictHostKeyChecking=no -P 2426 ${jarPath} ${host}:~/${jarName}"
scpDotEnv="-o StrictHostKeyChecking=no -P 2426 .env ${host}:~/.env"

sshStartJar="-o StrictHostKeyChecking=no -T -p 2426 ${host} \
  <<EOF
  cd
  screen -dm bash -c '
  sudo ~/.sdkman/candidates/java/current/bin/java -jar ${jarName};
  exec bash'
EOF"

if [ -n "${identityFile}" ]; then
  chmod 400 "${identityFile}"
  sshStopAndRemoveOldJar="ssh -i ${identityFile} ${sshStopAndRemoveOldJar}"
  scpJar="scp -i ${identityFile} ${scpJar}"
  scpDotEnv="scp -i ${identityFile} ${scpDotEnv}"
  sshStartJar="ssh -i ${identityFile} ${sshStartJar}"
else
  sshStopAndRemoveOldJar="ssh ${sshStopAndRemoveOldJar}"
  scpJar="scp ${scpJar}"
  scpDotEnv="scp ${scpDotEnv}"
  sshStartJar="ssh ${sshStartJar}"
fi

echo "* Stopping and removing old jar ..."
if ! eval "$sshStopAndRemoveOldJar"; then
  exit 1
fi

echo "* Uploading ${jarName} ..."
if ! eval "$scpJar"; then
  exit 1
fi

echo "* Uploading .env ..."
if ! eval "$scpDotEnv"; then
  exit 1
fi

echo "* Starting ${jarName} ..."
if ! eval "$sshStartJar"; then
  exit 1
fi

if [ -n "${identityFile}" ]; then
  rm "${identityFile}"
fi
