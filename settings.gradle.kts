rootProject.name = "JavaProfessional"
include("hw01-gradle")
include("hw02-generic-collections")
include("hw03-reflection-annotations")
include("hw04-garbage-collector")
include("hw05-aop")
include("hw06-atm")
include("hw07-message-handler")
include("hw08-json")
include("hw09-jdbc")
include("hw10-hibernate")
include("hw11-cache")
include("hw13-ioc-container")
include("hw15-thread-synchronization")
include("hw16-queues")
include("hw17-grpc")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings
    val sonarlint: String by settings
    val spotless: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
        id("name.remal.sonarlint") version sonarlint
        id("com.diffplug.spotless") version spotless
    }
}
include("hw17-grpc")
