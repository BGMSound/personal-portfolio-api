plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.application)
    implementation(projects.infrastructure)
    implementation(projects.presentation)

    implementation(spring.web)
    implementation(spring.webflux)
    implementation(spring.security)
    implementation(spring.data.jpa)
    implementation(spring.thymeleaf)
    implementation(spring.aop)

    implementation(database.mysql)
}

tasks {
    bootJar {
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("output"))
    }
}