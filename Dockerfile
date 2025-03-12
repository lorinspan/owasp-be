# Folosim o imagine oficiala OpenJDK pentru a rula aplicatia Spring Boot
FROM openjdk:21-jdk-slim

# Setam directorul de lucru in container
WORKDIR /app

# Copiem fisierele Maven si descarcam dependentele
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw  # Adaugă această linie
RUN ./mvnw dependency:go-offline

# Copiem sursele proiectului
COPY src ./src

# Construim aplicatia
RUN ./mvnw package -DskipTests

# Setam portul pe care ruleaza aplicatia
EXPOSE 8080

# Comanda pentru a rula aplicatia
CMD ["java", "-jar", "target/owasp-be-0.0.1-SNAPSHOT.war"]