plugins {
    id("java")
}

group = "ru.nicshal.advanced"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("ch.qos.logback:logback-classic")
    implementation ("org.assertj:assertj-core")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}