plugins {
    id("com.android.application")
    kotlin("android")

    id("com.github.ben-manes.versions") version "0.38.0"
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

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta06"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.2.0")

    implementation("androidx.compose.ui:ui:1.0.0-beta06")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta06")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta06")
    implementation("androidx.compose.material:material:1.0.0-beta06")

    implementation("androidx.activity:activity-compose:1.3.0-alpha03")
}
