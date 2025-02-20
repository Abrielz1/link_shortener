FROM openjdk:17-jdk-slim
LABEL authors="Abriel"
WORKDIR /app
COPY target/link_shortener-0.0.1-SNAPSHOT.jar link_shortener.jar
CMD ["java","-jar","link_shortener.jar"]
