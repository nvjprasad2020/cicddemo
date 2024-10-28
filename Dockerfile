FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/cicd-demo-0.0.1.jar /app/cicd-demo.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/cicd-demo.jar"]
