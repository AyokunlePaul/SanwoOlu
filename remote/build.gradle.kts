import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Dependencies.ProjectConstants.COMPILE_SDK)
    defaultConfig {
        minSdkVersion(Dependencies.ProjectConstants.MINIMUM_SDK)
        targetSdkVersion(Dependencies.ProjectConstants.TARGET_SDK)
        buildConfigField("String", "BASE_URL", getProperty("BASE_URL"))
        buildConfigField("String", "APP_ID", getProperty("APP_ID"))
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", version = Dependencies.Versions.Kotlin.CORE))
    implementation(Dependencies.Util.TIMBER)
    implementRemote()
}

fun getProperty(key: String): String {
    return try {
        val properties = loadProperties("local.properties")
        properties.getProperty(key)
    } catch (exception: Exception) {
        getEnvironmentVariable(key)
    }
}

fun getEnvironmentVariable(key: String): String {
    return System.getenv(key)
}