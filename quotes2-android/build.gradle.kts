plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(Android.compile_sdk)

    defaultConfig {
        applicationId = "io.github.dector.quotes"

        versionCode = Android.version_code
        versionName = Android.version_name

        minSdkVersion(Android.min_sdk)
        targetSdkVersion(Android.target_sdk)

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.2.0")
}
