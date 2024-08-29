import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    idea
}

val kotlinVersion = libs.versions.kotlinVersion.get()

dependencies {
    implementation(libs.apache.lang)

    compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // migraiton
    implementation(platform(libs.mongock.bom))
    // test
    testImplementation(libs.kotest.core)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.datest)

}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.apply {
    freeCompilerArgs += "-Xjsr305=strict -Xemit-jvm-type-annotations"
}
