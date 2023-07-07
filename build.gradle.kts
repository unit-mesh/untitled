plugins {
    java
    id("org.springframework.boot") version "2.7.10"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "cc.unitmesh.untitled"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.projectlombok:lombok:1.18.26")

    implementation("io.swagger:swagger-annotations:1.6.11")

    implementation("org.mybatis:mybatis:3.5.13")
    implementation("com.baomidou:mybatis-plus:3.5.3.1")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.flywaydb:flyway-core")

    // h2
    implementation("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
