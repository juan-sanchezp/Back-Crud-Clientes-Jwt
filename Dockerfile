# ---------- STAGE 1: build ----------
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# 1️⃣ Copiar solo lo necesario para dependencias
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw \
    && sed -i 's/\r$//' mvnw \
    && ./mvnw dependency:go-offline

# 2️⃣ Ahora sí copiar el código
COPY src src

# 3️⃣ Build final
RUN ./mvnw clean package -DskipTests

# ---------- STAGE 2: runtime ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]





##ESTO ESTABA Recompilando todo el proyecto cada vez que se hacía un cambio en el código, incluso si solo se modificaba una línea. Esto se debía a que el comando `COPY . .` copiaba todo el proyecto, lo que invalidaba la caché de Docker y obligaba a recompilar desde cero.

# #Stage 1: Build the aplication
# FROM eclipse-temurin:17-jdk AS builder

# WORKDIR /app

# COPY . .

# RUN chmod +x mvnw \
#     && sed -i 's/\r$//' mvnw \
#     && ./mvnw clean package -DskipTests

# #Stege 2: Run the aplication
# FROM eclipse-temurin:17-jre

# WORKDIR /app

# COPY --from=builder /app/target/*.jar app.jar

# EXPOSE 8080

# ENTRYPOINT [ "java", "-jar", "app.jar"]