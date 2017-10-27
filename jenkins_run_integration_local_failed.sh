#!/bin/bash
sbt -Dbrowser=firefox 'test-only uk.gov.hmrc.integration.cucumber.utils.RunnerFailed'

