import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    idea
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven {
            url = uri("https://repo.spring.io/milestone")
        }
    }

}

val kotlinVersion = libs.versions.kotlinVersion.get()
val springBom = libs.spring.bom

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    dependencies {
        implementation(platform(springBom))
        implementation(kotlin("stdlib", kotlinVersion))
        implementation(kotlin("reflect", kotlinVersion))
        implementation(kotlin("stdlib-jdk8", kotlinVersion))

    }
}
