dependencies {
    implementation(project(":module:domain"))
    implementation(project(":module:application"))
    implementation(project(":module:infrastructure"))

    implementation(spring.web)
    implementation(spring.data.jpa)
    implementation(database.mysql)
}