dependencies {
    implementation(projects.module.domain)
    implementation(projects.module.application)

    implementation(spring.webflux)
    implementation(spring.data.jpa)

    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    implementation(lib.ulid.generator)
    implementation(lib.mapstruct.core)
    annotationProcessor(lib.mapstruct.processor)

    implementation(database.mysql)
}