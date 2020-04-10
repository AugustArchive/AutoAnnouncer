import kr.entree.spigradle.kotlin.paper

plugins {
  id("com.github.johnrengelman.shadow") version "4.0.1"
  id("kr.entree.spigradle") version "1.2.4"
  kotlin("jvm") version "1.3.71"
  java
}

val ver = Version(1, 1, 0)
group = "dev.august"
version = ver.get()

repositories {
  mavenCentral()
  maven(url = "https://papermc.io/repo/repository/maven-public/")
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
  implementation(kotlin("stdlib-jdk8"))
  compileOnly(paper("1.15.2"))
}

tasks {
  build {
    dependsOn(shadowJar)
  }

  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }

  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}

class Version(private val major: Int, private val minor: Int, private val revision: Int) {
  fun get(): String = "$major.$minor.$revision"
}