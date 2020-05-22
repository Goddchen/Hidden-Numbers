plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

group = "de.goddchen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:7.0.1")
    implementation("com.graphql-java-kickstart:graphql-java-tools:6.0.2")
    runtimeOnly("com.graphql-java-kickstart:altair-spring-boot-starter:7.0.1")
    runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:7.0.1")
    runtimeOnly("com.graphql-java-kickstart:voyager-spring-boot-starter:7.0.1")
    runtimeOnly("com.graphql-java-kickstart:playground-spring-boot-starter:7.0.1")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}