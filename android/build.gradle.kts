plugins {
    id("com.android.application")
    kotlin("android")
    //kotlin("kapt")
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
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.kotlinx_coroutines_core)
    implementation(Deps.kotlinx_coroutines_android)

    implementation(project(":quotes-domain"))
    implementation(project(":quotes-common"))
    implementation(project(":quotes-storage"))
    implementation(project(":quotes-repositories"))
    implementation(project(":presentation"))

    // Android support
    implementation("androidx.appcompat:appcompat:1.1.0")

    implementation(Deps.anvil)

    // Rx
    //implementation("io.reactivex:rxandroid:1.1.0"
    //implementation("io.reactivex:rxjava:1.1.0"
    //implementation("io.reactivex:rxkotlin:0.40.1"

    // DI - Koin
    implementation(Deps.koin_android)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.0.0-beta4")
    implementation("com.squareup.retrofit2:converter-gson:2.0.0-beta4")

    androidTestImplementation("com.android.support:support-annotations:28.0.0")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test:rules:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
