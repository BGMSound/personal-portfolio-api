plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(projects.api)
    implementation(projects.application)
    implementation(projects.infrastructure)
    implementation(projects.application)

    implementation(spring.thymeleaf)
    implementation(spring.webflux)
    implementation(spring.aop)

    implementation(database.flyway)
    implementation(database.flyway.mysql)
}

tasks {
    bootJar {
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("output"))
    }
}