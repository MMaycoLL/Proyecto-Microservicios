#
# Etapa de compilación
#
FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar


#
# Etapa de empaquetado
#
FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/build/libs/*.jar java-app.jar
ENTRYPOINT ["java","-jar","java-app.jar"]