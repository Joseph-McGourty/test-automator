#!/bin/bash
ENV="local"
if [ "$1" = "dev" ] || [ "$1" = "qa" ] || [ "$1" = "staging" ]
then
    ENV="$1"
fi
echo "Environment : $ENV"

echo "BrowserStackLocal instances:"
pidof BrowserStackLocal
if pidof BrowserStackLocal; then
  echo "BrowserStackLocal running already"
else
	if [ -f ./BrowserStackLocal ];
	then
	   echo "File BrowserStackLocal exists."
	else
	   	wget https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip
  	    unzip BrowserStackLocal-linux-x64.zip
	fi
  . ./src/test/resources/browserConfig.properties
  ./BrowserStackLocal $automatekey &
fi

declare -a setups=("BS_Win7_Chrome_56")
#declare -a setups=("BS_ElCapitan_Firefox_51" "BS_Sierra_Chrome_56" "BS_Win7_Chrome_56" "BS_Win7_Firefox_51" "BS_Win7_IE_8" "BS_Win7_IE_11" "BS_Win8_IE_10" "BS_Win10_Chrome_56" "BS_Win10_Edge_14" "BS_Win10_Firefox_51" "BS_Win10_IE_11" "BS_Yosemite_Chrome_56")
for setup in "${setups[@]}"
do
    echo "******************** Loading config from $setup.json ********************"
    sbt -Dbrowser=browserstack -DtestDevice="$setup" -Denvironment=$ENV 'test-only uk.gov.hmrc.integration.cucumber.utils.RunnerBrowserStackTests'
    shift
done
