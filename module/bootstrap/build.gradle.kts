plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.api)
    implementation(projects.application)
    implementation(projects.persistence)
    implementation(projects.authentication)
    implementation(projects.application)

    implementation(spring.validation)
    implementation(spring.thymeleaf)
    implementation(spring.web)
    implementation(spring.security)
    implementation(spring.test)
}

tasks {
    bootJar {
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("output"))
    }
}