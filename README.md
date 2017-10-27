income-tax-subscription-agent-acceptance-tests
======================

This repo contain the acceptance tests for the income tax subscription service for Agents.

It is built using:

cucumber 1.1.8

java 1.8

Scala 2.11.7

SBT to build 0.13.8
    
### Getting started

Ensure that you have a working linux environment.

###  Execution
In /src/test/scala/uk/gov/hmrc/integration/cucumber there are scala classes which control what is run according to the tests tagged with the below tags. The main class is Runner which selects and runs tests which are tagged. You can run ./run_integration_local.sh to run tests against a local version of the application
