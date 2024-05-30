#
# Build stage
#
# Etapa de construcción
FROM gradle:7.6.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean
RUN gradle bootJar


#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/build/libs/*.jar groovy-app.jar
ENTRYPOINT ["java","-jar","groovy-app.jar"]