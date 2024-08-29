plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    idea
}

val kotlinVersion = libs.versions.kotlinVersion.get()

dependencies {
    implementation(project(":libs:common:shortener-common"))
    compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
}