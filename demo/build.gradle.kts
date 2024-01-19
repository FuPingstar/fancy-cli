plugins {
    application
    alias(libs.plugins.spring.boot)
//    alias(libs.plugins.shadow)
}

version = "1.0.0"
description = "spring shell sample"

dependencies {
    implementation(platform(libs.spring.shell.bom))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.shell:spring-shell-starter")

}