dependencies {
    api(projects.domain)

    runtimeOnly(projects.authentication)
    runtimeOnly(projects.persistence)
    runtimeOnly(projects.identification)

    implementation(spring.data.jpa)
}