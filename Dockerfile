# Folosim o imagine oficială OpenJDK pentru a rula aplicația Spring Boot
FROM openjdk:21-jdk-slim

# Setăm directorul de lucru în container
WORKDIR /app

# Copiem fișierele Maven și descărcăm dependențele (pentru caching eficient)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copiem sursele proiectului
COPY src ./src

# Construim aplicația
RUN ./mvnw package -DskipTests

# Setăm portul pe care rulează aplicația
EXPOSE 8080

# Comanda pentru a rula aplicația
CMD ["java", "-jar", "target/owasp-be-0.0.1-SNAPSHOT.war"]
