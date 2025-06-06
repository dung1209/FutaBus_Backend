# Stage 1: Build WAR file bằng Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY . ./FutaBus_Backend

WORKDIR /app/FutaBus_Backend
RUN mvn clean package -DskipTests

# Stage 2: Chạy bằng Tomcat
FROM tomcat:9-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/FutaBus_Backend/target/FutaBus_Backend-1.0.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8085

CMD ["catalina.sh", "run"]
