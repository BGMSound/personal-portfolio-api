dependencies {
    implementation(project(":module:domain"))
    implementation(project(":module:application"))

    implementation(spring.validation)
    implementation(spring.web)
    implementation(spring.security)
}