plugins {
    id ("groovy")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.uniquindio"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    implementation ("org.codehaus.groovy:groovy-all:3.0.9")
    implementation(kotlin("script-runtime"))
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-cache")
    implementation ("org.springframework.boot:spring-boot-starter-data-redis")
    implementation ("org.springframework.data:spring-data-redis:2.6.1")
    implementation ("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
    implementation ("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

}


tasks.withType<Test> {
    useJUnitPlatform()
}