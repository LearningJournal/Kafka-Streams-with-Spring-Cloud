

netsh interface portproxy add v4tov4 listenport=9092 listenaddress=0.0.0.0 connectport=9092 connectaddress=<your-ip>

confluent local services start

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic input-topic
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic output-topic

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic process-in-0
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic process-out-0

kafka-console-producer --broker-list localhost:9092 --topic process-in-0
kafka-console-consumer --topic process-out-0 --from-beginning --bootstrap-server localhost:9092

kafka-console-producer --broker-list localhost:9092 --topic input-topic
kafka-console-consumer --topic output-topic --from-beginning --bootstrap-server localhost:9092


confluent local destroy