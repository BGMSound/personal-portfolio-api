dependencies {
    implementation(project(":module:domain"))
    implementation(project(":module:application"))
    implementation(project(":module:infrastructure"))
    implementation(project(":module:presentation"))

    implementation(database.mysql)
    implementation(spring.data.jpa)
    implementation(spring.web)
    implementation(spring.security)
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

