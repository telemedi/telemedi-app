val micronautVersion = "2.0.0.M3"
val kotlinVersion = "1.3.72"
val exposedVersion = "0.25.1"

plugins {
  // Apply the Kotlin JVM plugin to add support for Kotlin.
  id("org.jetbrains.kotlin.jvm") version "1.3.72"
  id("org.jetbrains.kotlin.kapt") version "1.3.72"
  id("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
  id("com.github.johnrengelman.shadow") version "5.2.0"
  id("com.diffplug.gradle.spotless") version "4.0.1"

  // Apply the application plugin to add support for building a CLI application.
  application
  jacoco
}

repositories {
  // Use jcenter for resolving dependencies.
  // You can declare any Maven/Ivy/file repository here.
  jcenter()
}

dependencies {
  kapt(enforcedPlatform("io.micronaut:micronaut-bom:$micronautVersion"))
  kapt("io.micronaut:micronaut-inject-java")
  kapt("io.micronaut:micronaut-validation")
  implementation(enforcedPlatform("io.micronaut:micronaut-bom:$micronautVersion"))
  implementation("io.micronaut:micronaut-inject")
  implementation("io.micronaut:micronaut-validation")
  implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
  implementation("io.micronaut:micronaut-runtime")
  implementation("javax.annotation:javax.annotation-api")
  implementation("io.micronaut:micronaut-http-server-netty")
  implementation("io.micronaut:micronaut-http-client")
  implementation("io.micronaut.graphql:micronaut-graphql")
  implementation("org.postgresql:postgresql:42.2.12")
  runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
  runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8") // Align versions of all Kotlin components
  runtimeOnly("io.micronaut.configuration:micronaut-jdbc-hikari")
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  kaptTest(enforcedPlatform("io.micronaut:micronaut-bom:$micronautVersion"))
  kaptTest("io.micronaut:micronaut-inject-java")

  testImplementation(enforcedPlatform("io.micronaut:micronaut-bom:$micronautVersion"))
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("io.micronaut.test:micronaut-test-kotlintest")
  testImplementation("io.mockk:mockk:1.9.3")
  // Use the Kotlin JUnit integration.
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
  testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

application {
  // Define the main class for the application.
  mainClassName = "co.avaldes.telemedi.Application"
}

spotless {
  kotlin {
    ktlint().userData(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
    licenseHeaderFile(".copyright")
  }
  kotlinGradle {
    target("*.gradle.kts")
    ktlint().userData(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
  }
}

tasks.check {
  dependsOn(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
  reports {
    xml.isEnabled = true
    csv.isEnabled = false
    html.isEnabled = false
  }
}
