plugins {
    id("java")
}

group = "ru.nicshal"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic")

    testImplementation ("org.junit.jupiter:junit-jupiter-api")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
}
