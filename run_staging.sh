#!/bin/bash
sbt -Dbrowser=firefox -Denvironment=staging 'test-only uk.gov.hmrc.integration.cucumber.utils.Runner'

