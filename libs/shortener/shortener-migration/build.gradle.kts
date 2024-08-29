plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    idea
    jacoco
}

dependencies {
    // migraiton
    implementation(platform(libs.mongock.bom))
    implementation("io.mongock:mongock-springboot-v3")
    implementation("io.mongock:mongodb-springdata-v4-driver")
    compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")

    implementation(project(":libs:shortener:shortener-model"))
}