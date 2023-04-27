plugins {
    kotlin("jvm") version "1.6.10"
    java
}

group = "ru.erius"
version = kotlin.coreLibrariesVersion
val serverPath = "C:/mc_servers/vanilla/plugins"
val mainClass = "$group.${name.toLowerCase()}.$name"

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")
}

tasks.processResources {
    expand("name" to project.name, "version" to version, "main" to mainClass)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    doLast {
        copy {
            from(destinationDirectory)
            into(serverPath)
        }
    }
}