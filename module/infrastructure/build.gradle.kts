dependencies {
    implementation(project(":module:domain"))
    implementation(project(":module:application"))

    implementation(spring.web)
    implementation(spring.webflux)
    implementation(spring.data.jpa)

    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    implementation(lib.ulid.generator)

    implementation(database.mysql)
}