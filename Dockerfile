# Use a imagem oficial do OpenJDK 17 como imagem base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo jar empacotado para o contêiner
COPY target/lanchonete-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta em que a aplicação será executada
EXPOSE 8080

# Executa o arquivo jar
ENTRYPOINT ["java", "-jar", "app.jar"]