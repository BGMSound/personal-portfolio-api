dependencies {
    implementation(projects.application)

    api(spring.validation)
    api(spring.web)
    api(spring.security)
}

projectGrapher {
    group = "entrypoint"
}