# Kafka Commands

- **Local de Instalação:** C:\apps\kafka\kafka_2.12-2.3.1

- **Subir Zookeeper:** .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

- **Subir Kafka:** .\bin\windows\kafka-server-start.bat .\config\server.properties

- **Criar tópico:** .\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVO_PEDIDO

- **Listar tópicos:** .\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

- **Descrever tópicos:** .\bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092

- **Enviar mensagem:** .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic LOJA_NOVO_PEDIDO

- **Consumir de novas mensagens:** .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO

- **Consumir todos os pedidos armazenados:** .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO --from-beginning
