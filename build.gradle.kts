plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.flywaydb.flyway") version "9.22.0"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core:10.17.2")
    implementation("org.flywaydb:flyway-database-postgresql:10.17.2")
    runtimeOnly("org.postgresql:postgresql")
    val telegram_bot_version="0.10.2b"
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:$telegram_bot_version")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

task("flywayMigrateLocal", org.flywaydb.gradle.task.FlywayMigrateTask::class) {
    description = "Migrates the local database"
    url = System.getenv("POSTGRES_URL") ?: "jdbc:postgresql://localhost:5432/postgres"
    user = System.getenv("POSTGRES_USER") ?: "postgres"
    password = System.getenv("POSTGRES_PASSWORD") ?: "postgres"
    locations = arrayOf(
        "filesystem:src/main/resources/db/migration"
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}
