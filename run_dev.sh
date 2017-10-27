#!/bin/bash
ENV="dev"
BROWSER="firefox"
if [ "$1" = "dev" ] || [ "$1" = "qa" ] || [ "$1" = "staging" ]
then
    ENV="$1"
fi
if [ "$2" = "firefox" ] || [ "$2" = "chrome" ]
then
    BROWSER="$2"
    fi

if [ $BROWSER = "firefox" ]
then
    sbt -Dbrowser=firefox -Denvironment=$ENV 'test-only uk.gov.hmrc.integration.cucumber.utils.Runner' #| grep -v "error"
elif [ $BROWSER = "chrome" ]
then
    sbt -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver -Dbrowser=chrome -Denvironment=$ENV 'test-only uk.gov.hmrc.integration.cucumber.utils.Runner' #| grep -v "error"
fi
