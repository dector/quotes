buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = Kotlin.version))
        classpath("com.android.tools.build:gradle:4.0.1")
    }
}

allprojects {
    version = Project.version

    repositories {
        jcenter()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete("build")
}
