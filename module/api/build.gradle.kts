plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(projects.application)

    implementation(spring.validation)
    implementation(spring.thymeleaf)
    implementation(spring.web)
    implementation(spring.security)
}

tasks {
    bootJar {
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("output"))
    }
}

projectGrapher {
    group = "entrypoint"
}