pluginManagement {
    fun property(key: String): String {
        return extra[key]?.toString() ?: throw IllegalArgumentException("property with $key not found")
    }
    plugins {
        kotlin("jvm") version property(key = "kotlin-version")
        kotlin("plugin.spring") version property("kotlin-version")
        kotlin("plugin.jpa") version property("kotlin-version")

        id("org.springframework.boot") version property("spring-boot-version")
        id("io.spring.dependency-management") version "1.1.4"
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("spring") {
            library("web", "org.springframework.boot:spring-boot-starter-web:${property("spring-boot-version")}")
            library("webflux", "org.springframework.boot:spring-boot-starter-webflux:${property("spring-boot-version")}")
            library("aop", "org.springframework.boot:spring-boot-starter-aop:${property("spring-boot-version")}")
            library("validation", "org.springframework.boot:spring-boot-starter-validation:${property("spring-boot-version")}")
            library("data-jpa", "org.springframework.boot:spring-boot-starter-data-jpa:${property("spring-boot-version")}")
            library("security", "org.springframework.boot:spring-boot-starter-security:${property("spring-boot-version")}")
            library("thymeleaf", "org.springframework.boot:spring-boot-starter-thymeleaf:${property("spring-boot-version")}")
            library("test", "org.springframework.boot:spring-boot-starter-test:${property("spring-boot-version")}")
        }
        create("lib") {
            library("jwt-api", "io.jsonwebtoken:jjwt-api:${property("jwt-version")}")
            library("jwt-impl", "io.jsonwebtoken:jjwt-impl:${property("jwt-version")}")
            library("jwt-jackson", "io.jsonwebtoken:jjwt-jackson:${property("jwt-version")}")
            library("ulid-generator", "com.github.f4b6a3:ulid-creator:5.2.3")
        }
        create("database") {
            library("mysql", "mysql:mysql-connector-java:${property("mysql-version")}")
        }
    }
}


rootProject.name = property("project-name")
includeAll("module")

fun includeAll(modulesDir: String) {
    file("${rootProject.projectDir.path}/${modulesDir.replace(":", "/")}/").listFiles()?.forEach { modulePath ->
        include("${modulesDir.replace("/", ":")}:${modulePath.name}")
    }
}

fun property(key: String): String {
    return extra[key]?.toString() ?: throw IllegalArgumentException("property with $key not found")
}