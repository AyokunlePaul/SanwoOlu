plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Dependencies.ProjectConstants.COMPILE_SDK)
    buildToolsVersion(Dependencies.ProjectConstants.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Dependencies.ProjectConstants.MINIMUM_SDK)
        targetSdkVersion(Dependencies.ProjectConstants.TARGET_SDK)
        applicationId = Dependencies.ProjectConstants.ApplicationId.BASE
        versionCode = Dependencies.ProjectConstants.VERSION_CODE
        versionName = Dependencies.ProjectConstants.VERSION_NAME
        multiDexEnabled = true
        testInstrumentationRunner = Dependencies.ProjectConstants.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
        named("debug") {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
        }
    }
    buildFeatures.viewBinding = true

    sourceSets {
        val mainSrcSet = project.file("src/main/kotlin")
        findByName("main")?.java?.srcDirs(mainSrcSet)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.COROUTINE)
    implementation(Dependencies.Kotlin.COROUTINE_ANDROID)
    implementation(Dependencies.AndroidX.PLAY_CORE)
    implementHilt()
}