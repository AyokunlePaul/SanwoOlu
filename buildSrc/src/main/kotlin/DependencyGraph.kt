import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

fun DependencyHandler.implementVanillaHilt() {
    buildSrcImplementation(Dependencies.Hilt.CORE)
    kapt(Dependencies.Hilt.COMPILER)
}

fun DependencyHandler.implementHilt() {
    buildSrcImplementation(Dependencies.Hilt.ANDROID, Dependencies.Hilt.VIEWMODEL)
    androidTestImplementation(Dependencies.Hilt.TEST)
    kapt(Dependencies.Hilt.COMPILER, Dependencies.Hilt.ANDROID_X_COMPILER)
}

fun DependencyHandler.implementAndroidX() {
    api(
        Dependencies.AndroidX.APP_COMPAT, Dependencies.AndroidX.CORE_KTX,
        Dependencies.AndroidX.MATERIAL, Dependencies.AndroidX.FRAGMENT_KTX,
        Dependencies.AndroidX.ACTIVITY_KTX, Dependencies.AndroidX.APP_COMPAT,
        Dependencies.AndroidX.VIEWMODEL_KTX, Dependencies.AndroidX.CONSTRAINT_LAYOUT
    )
}

fun DependencyHandler.implementNavigation() {
    api(
        Dependencies.Navigation.RUNTIME, Dependencies.Navigation.FRAGMENT,
        Dependencies.Navigation.UI, Dependencies.Navigation.FEATURE_MODULE
    )
    androidTestImplementation(
        Dependencies.Navigation.TESTING
    )
}

fun DependencyHandler.implementLocal() {
    implementHilt()
    buildSrcImplementation(
        Dependencies.Room.RUNTIME, Dependencies.Room.KTX, Dependencies.Util.COIL,
        Dependencies.Kotlin.COROUTINE
    )
    kapt(Dependencies.Room.COMPILER)
    testImplementation(
        Dependencies.Room.TEST
    )
}

fun DependencyHandler.implementRemote() {
    api(
        Dependencies.Network.RETROFIT, Dependencies.Network.GSON_CONVERTER,
        Dependencies.Network.OKHTTP, Dependencies.Network.LOGGING_INTERCEPTOR
    )
    buildSrcImplementation(Dependencies.Kotlin.COROUTINE)
    implementHilt()
    testImplementation(Dependencies.Network.MOCK_WEB_SERVER)
}

fun DependencyHandler.implementData() {
    kotlin("stdlib-jdk8", version = Dependencies.Versions.Kotlin.CORE)
    implementVanillaHilt()
    add("implementation", Dependencies.Kotlin.COROUTINE)
}