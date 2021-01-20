

netsh interface portproxy add v4tov4 listenport=9092 listenaddress=0.0.0.0 connectport=9092 connectaddress=<your-ip>

confluent local services start

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic user-clicks-topic

kafka-console-producer --broker-list localhost:9092 --topic user-clicks-topic \
--property parse.key=true --property key.separator=":"

confluent local destroy