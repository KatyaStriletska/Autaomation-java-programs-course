plugins {
    id("java")
    kotlin("jvm") version "1.8.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    testCompileOnly("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("displayDependencies") {
    group = "build"
    description = "Displays dependencies versions"
    dependsOn("checkFileExistence")
    doLast {
        val configurations: Set<Configuration> = project.configurations
        configurations.forEach { configuration ->
            val dependencies = configuration.dependencies
            dependencies.forEach { dependency ->
                println("${dependency.group}:${dependency.name}:${dependency.version}")
            }
        }
    }
}

abstract class CheckFileTask : DefaultTask() {

    @get:Input
    lateinit var sourceFilePath: String

    @TaskAction
    fun generateReport() {
        val sourceFile = File(sourceFilePath)
        if (!sourceFile.exists()) {
            println("Directory '${sourceFile}' doesn't exist!")
        } else {
            println("Directory '${sourceFile}' exists!")
        }
    }
}

tasks.register<CheckFileTask>("checkFileExistence") {
    sourceFilePath = "src/main/l"
}

tasks.compileJava(){
    dependsOn("displayDependencies")
};