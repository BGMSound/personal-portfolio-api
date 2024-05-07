dependencies {
    implementation(projects.application)

    implementation(spring.starter)
    implementation(spring.aop)
    implementation(spring.webflux)

    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
}

projectGrapher {
    group = "infrastructure"
}