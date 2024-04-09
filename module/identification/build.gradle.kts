dependencies {
    api(projects.domain)

    implementation(spring.starter)
    implementation(lib.ulid.generator)
}

projectGrapher {
    group = "infrastructure"
}