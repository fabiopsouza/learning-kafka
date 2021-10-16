# Comandos

- **Local de Instalação**: C:\apps\kafka\kafka_2.12-2.3.1

- CRIAR TÓPICO
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVO_PEDIDO

- LISTAR TÓPICOS
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

- DESCREVER OS TÓPICOS
.\bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092

- ENVIAR MENSAGEM
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic LOJA_NOVO_PEDIDO

- CONSUMIDOR DE NOVAS MENSAGENS
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO

- CONSUMIDOR DE TODOS OS PEDIDOS
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO --from-beginning
