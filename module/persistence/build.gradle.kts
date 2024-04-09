dependencies {
    api(projects.domain)
    compileOnly(projects.application)
    implementation(projects.authentication)

    implementation(spring.webflux)
    implementation(spring.data.jpa)
    implementation(spring.aop)

    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    implementation(lib.ulid.generator)

    implementation(database.mysql)
}

projectGrapher {
    group = "infrastructure"
}