plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    idea
    jacoco
}

dependencies {

    api(project(":libs:shortener:shortener-model"))
    api(project(":libs:common:shortener-common"))

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // test
    testImplementation(libs.kotest.core)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.datest)

}