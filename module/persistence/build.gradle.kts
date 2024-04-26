dependencies {
    api(projects.domain)
    compileOnly(projects.application)
    implementation(projects.authentication)

    api(spring.webflux)
    api(spring.data.jpa)
    api(spring.aop)

    api(lib.jwt.api)
    runtimeOnly(lib.jwt.jackson)
    runtimeOnly(lib.jwt.impl)
    api(lib.ulid.generator)

    api(database.mysql)
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