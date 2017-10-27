#!/bin/sh
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_51

echo "BrowserStackLocal instances:"
pidof BrowserStackLocal

#browserConfig properties file location
export PROPS_LOC="" #TODO: Add browserstack properties location

cd /tmp

export PATH=$PATH:$PROPS_LOC
echo "The path is $PATH"

. browserConfig.properties
echo ipxN1XNHdM93N9PMiwWY

if pidof BrowserStackLocal; then
  echo "BrowserStackLocal running already"
else
  if [ ! -e BrowserStackLocal ]; then
    wget https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip
    unzip BrowserStackLocal-linux-x64.zip
  fi

  ./BrowserStackLocal ipxN1XNHdM93N9PMiwWY &

fi
#export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_51
#
#sudo rabbitmqctl stop_app
#sudo rabbitmqctl reset
#sudo rabbitmqctl start_app
#
#echo "Using snapshot versions..."
#
#shopt -s expand_aliases
#alias smc="sm --config /etc/smserver/conf"
#
#smc --stop ALL
#smc --cleanlogs
#
#smc --start AWRS_ALL -f --wait 90 --noprogress
#
#echo "BrowserStackLocal instances:"
#pidof BrowserStackLocal
#
#if pidof BrowserStackLocal; then
#  echo "BrowserStackLocal running already"
#else
#  wget https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip
#  unzip BrowserStackLocal-linux-x64.zip
#  ./BrowserStackLocal muFgSyPrcWjMjyM9R2fd &
#fi