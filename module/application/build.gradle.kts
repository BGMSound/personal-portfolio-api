dependencies {
    api(projects.domain)
    runtimeOnly(projects.infrastructure)

    implementation(spring.data.jpa)
}