dependencies {
    api(projects.domain)

    api(spring.starter)
    api(lib.ulid.generator)
}

projectGrapher {
    group = "infrastructure"
}