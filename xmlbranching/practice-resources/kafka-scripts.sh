

netsh interface portproxy add v4tov4 listenport=9092 listenaddress=0.0.0.0 connectport=9092 connectaddress=<your-ip>
netsh interface portproxy add v4tov4 listenport=8081 listenaddress=0.0.0.0 connectport=8081 connectaddress=<your-ip>

confluent local services start

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic xml-order-topic

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic error-topic

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic india-orders

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic abroad-orders

kafka-console-producer --topic xml-order-topic --broker-list localhost:9092

kafka-console-consumer --bootstrap-server localhost:9092 --topic india-orders --from-beginning --property print.key=true --property key.separator=":"
kafka-console-consumer --bootstrap-server localhost:9092 --topic abroad-orders --from-beginning --property print.key=true --property key.separator=":"
kafka-console-consumer --bootstrap-server localhost:9092 --topic error-topic --from-beginning --property print.key=true --property key.separator=":"

confluent local services stop
confluent local destroy