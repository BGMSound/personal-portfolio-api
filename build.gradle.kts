import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

    id("io.spring.dependency-management")
    id("kr.junhyung.project-grapher") version "0.0.1-beta1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

projectGrapher {
    projectName = "personal-portfolio-api"
    nodeDistance = 0.9
    dpi = 500
}

subprojects {
    group = property(key = "project-group")
    version = property(key = "project-version")

    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test:${property("spring-boot-version")}")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs += "-Xjsr305=strict"
                jvmTarget = "17"
            }
        }
        withType<Test> {
            useJUnitPlatform()
        }
    }
}

fun property(key: String): String {
    return extra[key]?.toString() ?: throw IllegalArgumentException("property with $key not found")
}
