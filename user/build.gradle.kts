plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}
android {
    compileSdkVersion(Dependencies.ProjectConstants.COMPILE_SDK)
    buildToolsVersion(Dependencies.ProjectConstants.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Dependencies.ProjectConstants.MINIMUM_SDK)
        targetSdkVersion(Dependencies.ProjectConstants.TARGET_SDK)
        applicationId = Dependencies.ProjectConstants.ApplicationId.USER
        versionCode = Dependencies.ProjectConstants.VERSION_CODE
        versionName = Dependencies.ProjectConstants.VERSION_NAME

        testInstrumentationRunner = Dependencies.ProjectConstants.TEST_INSTRUMENTATION_RUNNER
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
    }
    buildFeatures.viewBinding = true
    sourceSets {
        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
            getByName("test").java.srcDirs("src/test/kotlin")
            getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Util.COIL)
    implementHilt()
    implementation(project(":app"))
}