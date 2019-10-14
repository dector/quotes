buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.0-beta01")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")
}

tasks.register("clean", Delete::class) {
    delete("build")
}
