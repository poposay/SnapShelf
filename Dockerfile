FROM eclipse-temurin:17.0.8_7-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw mvnw.cmd pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
RUN ./mvnw package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "SnapShelf-0.0.1-SNAPSHOT.jar"]