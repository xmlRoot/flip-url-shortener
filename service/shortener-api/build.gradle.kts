import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    idea
    jacoco
}

object Jackson {
    const val core = "com.fasterxml.jackson.core:jackson-core"
    const val databind = "com.fasterxml.jackson.core:jackson-databind"
    const val annotations = "com.fasterxml.jackson.core:jackson-annotations"
    const val datatypeJsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.0"
    const val kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation(libs.spring.openapi)

    implementation(platform(libs.jackson.bom))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation(project(":libs:shortener:shortener-feature"))
    implementation(project(":libs:shortener:shortener-migration"))
    implementation(platform(libs.mongock.bom))
    implementation("io.mongock:mongock-springboot-v3")

    // test
    testImplementation(libs.kotest.core)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.datest)

}

val bootJar : BootJar by tasks

bootJar.apply {
    enabled = true
    val projectVersionFragment = properties["ver"] ?: project.version
    archiveVersion.set(projectVersionFragment.toString())
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
    }
}

springBoot {
    buildInfo {
        properties {
            version.set(bootJar.archiveVersion)
            group.set(project.group.toString())
            additional.set(mapOf("git" to (project.properties["gitsha"]?.toString() ?: "-")))
        }
    }
}