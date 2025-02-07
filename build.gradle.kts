plugins {
    java
    id("org.springframework.boot") version "2.7.10"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm")
}

group = "cc.unitmesh.untitled"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.json:json:20231013")

    testImplementation("junit:junit:4.13.1")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("io.swagger:swagger-annotations:1.6.11")
    compileOnly("org.projectlombok:lombok:1.18.28")

    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}