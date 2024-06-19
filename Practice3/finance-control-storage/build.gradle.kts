plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":finance-model")))
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("--add-exports", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED"))
}
tasks.test {
    useJUnitPlatform()
}