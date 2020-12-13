

netsh interface portproxy add v4tov4 listenport=9092 listenaddress=0.0.0.0 connectport=9092 connectaddress=<your-ip>

netsh interface portproxy add v4tov4 listenport=8081 listenaddress=0.0.0.0 connectport=8081 connectaddress=<your-ip>

confluent local services start

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic loyalty-topic
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic hadoop-sink-topic


http://localhost:9021/

confluent local services stop
confluent local destroy