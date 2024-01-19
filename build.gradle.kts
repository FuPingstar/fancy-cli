version = "1.0.0"
group = "priv.jacob"

subprojects {
    apply(plugin = "java")

    dependencies {
        testImplementation(rootProject.libs.junit.jupiter)
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()

        maxHeapSize = "1G"
        failFast = true
        enabled = true

        testLogging {
            events("passed")
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
            vendor.set(JvmVendorSpec.ORACLE)
        }
        withSourcesJar()
        withJavadocJar()
    }
}