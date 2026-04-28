FROM eclipse-temurin:21-jre-alpine

COPY build/libs/*.jar /app/district.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/district.jar"]