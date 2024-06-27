dependencies {
    implementation(projects.domain)
    compileOnly(projects.application)

    annotationProcessor(jakarta.annotation.api)
    annotationProcessor(jakarta.persistence.api)

    implementation(spring.webflux)
    implementation(spring.data.jpa)
    implementation(spring.aop)

    implementation(lib.ulid.generator)
    implementation(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    implementation(lib.ulid.generator)
    implementation(variantOf(lib.querydsl.jpa) {
        classifier("jakarta")
    })
    annotationProcessor(variantOf(lib.querydsl.apt) {
        classifier("jakarta")
    })
    implementation(database.mysql)
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}
noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

projectGrapher {
    group = "infrastructure"
}