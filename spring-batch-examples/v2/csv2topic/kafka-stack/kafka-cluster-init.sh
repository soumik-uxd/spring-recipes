#!/bin/bash
RETRY_COUNT=20
SLEEP_TIME=5
GET_CLUSTER_INFO_URL="http://$KAFKA_MANAGER_HOST/clusters/$KAFKA_CLUSTER_NAME"
CREATE_CLUSTER_COMMANDS="curl -L -X POST 'http://$KAFKA_MANAGER_HOST/clusters' -H 'Origin: $KAFKA_MANAGER_HOST' -H 'Referer: http://$KAFKA_MANAGER_HOST/addCluster' -H 'Host: $KAFKA_MANAGER_HOST' -H 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'name=$KAFKA_CLUSTER_NAME' --data-urlencode 'zkHosts=$ZK_HOSTS' --data 'kafkaVersion=$KAFKA_VERSION&jmxEnabled=true&jmxUser=&jmxPass=&pollConsumers=true&activeOffsetCacheEnabled=true&tuning.brokerViewUpdatePeriodSeconds=30&tuning.clusterManagerThreadPoolSize=2&tuning.clusterManagerThreadPoolQueueSize=100&tuning.kafkaCommandThreadPoolSize=2&tuning.kafkaCommandThreadPoolQueueSize=100&tuning.logkafkaCommandThreadPoolSize=2&tuning.logkafkaCommandThreadPoolQueueSize=100&tuning.logkafkaUpdatePeriodSeconds=30&tuning.partitionOffsetCacheTimeoutSecs=5&tuning.brokerViewThreadPoolSize=24&tuning.brokerViewThreadPoolQueueSize=1000&tuning.offsetCacheThreadPoolSize=24&tuning.offsetCacheThreadPoolQueueSize=1000&tuning.kafkaAdminClientThreadPoolSize=24&tuning.kafkaAdminClientThreadPoolQueueSize=1000&tuning.kafkaManagedOffsetMetadataCheckMillis=30000&tuning.kafkaManagedOffsetGroupCacheSize=1000000&tuning.kafkaManagedOffsetGroupExpireDays=7&securityProtocol=PLAINTEXT&saslMechanism=DEFAULT&jaasConfig='"
CURL_OPTIONS="--compressed -s -o /dev/null -w ''%{http_code}''"
if [[ -z "${ZK_HOSTS}" ]] || [[ -z "${KAFKA_MANAGER_HOST}" ]] || [[ -z "${KAFKA_CLUSTER_NAME}" ]]; then
  echo "Incorrect or null configuration options!"
  echo "KAFKA_MANAGER_HOST: $KAFKA_MANAGER_HOST"
  echo "KAFKA_CLUSTER_NAME: $KAFKA_CLUSTER_NAME"
  echo "ZK_HOSTS: $ZK_HOSTS"
  exit
else
	retries=0
	while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' http://$KAFKA_MANAGER_HOST)" != "200" ]] && [[ $retries -lt $RETRY_COUNT ]]; do
		echo "Connecting to $KAFKA_MANAGER_HOST , attempt #$retries"
		retries=$((retries+1))
		sleep $SLEEP_TIME;
	done
	echo "Connected to the Kafka Manager host."
	
	# Set clusters
	echo "Setting up Cluster: $KAFKA_CLUSTER_NAME"
	echo "Checking if the cluster already defined..."
	result=$(curl -s $GET_CLUSTER_INFO_URL)
	if [[ $result == *"Unknown cluster"* ]]; then
		echo "Creating cluster $KAFKA_CLUSTER_NAME"
		command="$CREATE_CLUSTER_COMMANDS $CURL_OPTIONS"
		echo "Creation status: $(eval $command)"
		echo "Cluster has been created."
	else
		echo "Cluster already exists!"
	fi
fi