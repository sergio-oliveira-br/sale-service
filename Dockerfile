# Usando uma imagem base do JDK 17,
# Como é uma aplicação Java, deve se utilizar uma imagem base que já inclui o JDK,
# necessário para rodar o Spring Boot.
FROM openjdk:21-jdk-slim

# Esta linha adiciona metadados ao Dockerfile, definindo quem criou ou é responsável pela imagem.
LABEL authors="sergiovinicio"

# Define o diretório de trabalho dentro do container.
# Isso facilita a organização dos arquivos dentro do container.
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven para o container
COPY target/sale-service-0.0.1-SNAPSHOT.jar sale-service.jar

# Expondo a porta que a aplicação usa.
# A porta exposta deve ser a mesma que o Spring Boot usa (por padrão, 8080).
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "sale-service.jar"]