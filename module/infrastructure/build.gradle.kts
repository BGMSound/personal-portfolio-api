dependencies {
    implementation(projects.module.domain)
    implementation(projects.module.application)

    implementation(spring.webflux)
    implementation(spring.data.jpa)
    implementation(spring.aop)

    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    implementation(lib.ulid.generator)

    implementation(database.mysql)
}