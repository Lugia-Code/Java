# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copia os arquivos de build (pom.xml + código)
COPY pom.xml .
COPY src ./src

# Faz o build do projeto
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o jar construído do estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
