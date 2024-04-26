dependencies {
    implementation(projects.application)

    implementation(spring.validation)
    implementation(spring.web)
    implementation(spring.security)
}

projectGrapher {
    group = "entrypoint"
}