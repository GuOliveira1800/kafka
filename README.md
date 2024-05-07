# Implementação de Sistema de Mensageria com Kafka

Este repositório contém a implementação de um sistema de mensageria utilizando a tecnologia Kafka. Aqui está um guia para executar o sistema utilizando Docker ou via linha de comando.

## Guia de Execução usando Docker:

1. Abra o terminal.
2. Navegue até o diretório do arquivo `compose.yaml`.
3. Execute o comando `docker-compose up`.
4. O Docker iniciará o Kafka e o Zookeeper e em seguida executará o arquivo `ProjetoKafkaApplication.java`.
5. Após isso, você pode executar o `EmailService.java` (para consumir o tópico `ECOMMERCE_SEND_EMAIL`) ou o `FraudDetectorService.java` (para consumir o tópico `ECOMMERCE_NEW_ORDER`) ou você pode optar por executar o `LogService.java` (para consumir os dois tópicos).

**Observações:**
- Se você estiver utilizando o IntelliJ IDEA, não é necessário seguir os passos 1, 2 e 3, pois o IntelliJ pode inicializar o Docker automaticamente.

## Guia de Execução via linha de comando:

1. Baixe o Kafka em [https://kafka.apache.org/downloads](https://kafka.apache.org/downloads).
2. Descompacte a pasta.
3. Abra o terminal.
4. Navegue até o diretório do arquivo `zookeeper-server-start.bat`.
5. Execute o comando `zookeeper-server-start.bat ../../config/zookeeper-properties`. Isso iniciará o Zookeeper.
6. Abra outro terminal.
7. Navegue até o diretório do arquivo `kafka-server-start.bat`.
8. Execute o comando `kafka-server-start.bat ../../config/server.properties`. Isso iniciará o Kafka.

**Observações:**
- Certifique-se de que os diretórios e nomes são os mesmos presentes no arquivo descompactado. Este guia foi elaborado para o ambiente Windows.
- Para instruções em ambientes Linux, consulte: [https://www.youtube.com/watch?v=E6E14P82EWc](https://www.youtube.com/watch?v=E6E14P82EWc).
- Para instruções em ambientes MacOS, consulte: [https://www.youtube.com/watch?v=zqyrxkBjpTQ](https://www.youtube.com/watch?v=zqyrxkBjpTQ).
