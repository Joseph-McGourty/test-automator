#!/bin/bash

if [ "$1" = "--help" ]
then
  echo "Instructions"
  echo "------------"
  echo "Use a parameter of dev, qa or staging to chose which test environment to use"
  echo "Omitting the parameter runs the tests on localhost:9770"
  exit 1
fi

ENV="local"
if [ "$1" = "dev" ] || [ "$1" = "qa" ] || [ "$1" = "staging" ]
then
    ENV="$1"
fi
echo "Environment : $ENV"

# Company Registration Acceptance Test tmp directory.

export PAYER_ACC_TEST_TEMP=$WORKSPACE/tmp/paye-registration-acceptance-tests
if [ -d $PAYER_ACC_TEST_TEMP ];
then
  echo "$PAYER_ACC_TEST_TEMP already exists"
else
  echo "Creating workspace temporary directory"
  mkdir -p $CPAYER_ACC_TEST_TEMP
fi

sbt -Djava.io.tmpdir=$PAYER_ACC_TEST_TEMP -Dbrowser=firefox -Denvironment=$ENV 'test-only uk.gov.hmrc.integration.cucumber.utils.Runner'

# Tidy up
