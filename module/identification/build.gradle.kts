dependencies {
    implementation(projects.application)

    implementation(spring.starter)
    implementation(lib.ulid.generator)
}

projectGrapher {
    group = "infrastructure"
}