# Use a imagem oficial do Java 17
FROM openjdk17-alpine
# Crie um diretório de trabalho no container
WORKDIR /app
# Copie todos os arquivos do projeto para o diretório de trabalho no container
COPY . /app

EXPOSE 8080

RUN ./mwnw spring-boot:run 

