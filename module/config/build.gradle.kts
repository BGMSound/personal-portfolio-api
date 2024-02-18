dependencies {
    implementation(projects.module.domain)
    implementation(projects.module.application)
    implementation(projects.module.infrastructure)
    implementation(projects.module.presentation)

    implementation(spring.web)
    implementation(spring.webflux)
    implementation(spring.security)
    implementation(spring.data.jpa)
    implementation(spring.thymeleaf)

    implementation(database.mysql)
}

tasks {
    bootJar {
        enabled = true
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(file(rootProject.projectDir.path + "/build/output"))
    }
}

