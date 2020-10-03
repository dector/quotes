import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = Kotlin.version))
        classpath("com.android.tools.build:gradle:4.2.0-alpha12")
    }
}

allprojects {
    version = Project.version

    repositories {
        jcenter()
        google()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.languageVersion = "1.4"
        kotlinOptions.useIR = true
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

tasks.register("clean", Delete::class) {
    delete("build")
}
