/*
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.0-beta01")
        classpath(kotlin("gradle-plugin", version = Kotlin.version))
    }
}
*/

allprojects {
    version = Project.version

    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete("build")
}
