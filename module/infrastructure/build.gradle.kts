dependencies {
    implementation(projects.module.domain)
    implementation(projects.module.application)

    implementation(spring.web)
    implementation(spring.webflux)
    implementation(spring.data.jpa)

    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    implementation(lib.ulid.generator)

    implementation(database.mysql)
}