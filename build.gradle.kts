buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

/*task clean(type: Delete) {
    delete rootProject.buildDir
}*/
