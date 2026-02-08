#Stage 1: Build the aplication
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw \
    && sed -i 's/\r$//' mvnw \
    && ./mvnw clean package -DskipTests

#Stege 2: Run the aplication
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar"]