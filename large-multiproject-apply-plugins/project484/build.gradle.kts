apply(plugin = "java-library")

apply(plugin = "eclipse")
apply(plugin = "idea")


repositories {

            maven {
                name = "MAVEN_CENTRAL"
                url = uri("https://repo.maven.apache.org/maven2/")
            }

}

println("project484")


    dependencies {

            "implementation"("commons-lang:commons-lang:2.5")
    "implementation"("commons-httpclient:commons-httpclient:3.0")
    "implementation"("commons-codec:commons-codec:1.2")
    "implementation"("org.slf4j:jcl-over-slf4j:1.7.10")
            "implementation"("com.googlecode:reflectasm:1.01")
            "testImplementation"("junit:junit:4.13")

            "implementation"(project(":project403"))
    "implementation"(project(":project443"))
    "implementation"(project(":project483"))

    }


allprojects {
    dependencies{

    }
}


val compilerMemory: String by project
val testRunnerMemory: String by project
val testForkEvery: String by project

tasks.withType<JavaCompile> {
    options.isFork = true
    options.isIncremental = true
    options.forkOptions.memoryInitialSize = compilerMemory
    options.forkOptions.memoryMaximumSize = compilerMemory
}
tasks.withType<GroovyCompile> {
    options.isFork = true
    options.isIncremental = true
    options.forkOptions.memoryInitialSize = compilerMemory
    options.forkOptions.memoryMaximumSize = compilerMemory
}

tasks.withType<Test> {

    minHeapSize = testRunnerMemory
    maxHeapSize = testRunnerMemory
    maxParallelForks = 1
    setForkEvery(testForkEvery.toLong())

    if (!JavaVersion.current().isJava8Compatible) {
        jvmArgs("-XX:MaxPermSize=512m")
    }
    jvmArgs("-XX:+HeapDumpOnOutOfMemoryError")
}

task<DependencyReportTask>("dependencyReport") {
    outputs.upToDateWhen { false }
    outputFile = buildDir.resolve("dependencies.txt")
}


group = "org.gradle.test.performance"
version = "2.0"