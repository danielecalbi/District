FROM eclipse-temurin:21-jre-alpine

COPY build/libs/*.jar /app/district.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/district.jar"]

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005