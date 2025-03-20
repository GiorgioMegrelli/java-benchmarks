plugins {
    id("java")
    id("me.champeau.jmh") version "0.7.3"
}

group = "ge.megrelli.giorgio"
version = "1.0"

val jmhVersion = "1.37"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:$jmhVersion")
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")
}

tasks.jmhJar {
    dependsOn(tasks.compileJmhJava)
}

tasks.jmh {
    fork.set(4)
    threads.set(2)
    iterations.set(5)
    warmupIterations.set(5)
}
