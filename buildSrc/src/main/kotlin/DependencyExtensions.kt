import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.buildSrcImplementation(value: String) {
    add("implementation", value)
}

fun DependencyHandler.buildSrcImplementation(vararg values: String) {
    values.forEach {
        buildSrcImplementation(it)
    }
}

fun DependencyHandler.api(value: String) {
    add("api", value)
}

fun DependencyHandler.api(vararg values: String) {
    values.forEach {
        api(it)
    }
}

fun DependencyHandler.kapt(value: String) {
    add("kapt", value)
}

fun DependencyHandler.kapt(vararg values: String) {
    values.forEach {
        kapt(it)
    }
}

fun DependencyHandler.testImplementation(value: String) {
    add("testImplementation", value)
}

fun DependencyHandler.testImplementation(vararg values: String) {
    values.forEach {
        testImplementation(it)
    }
}

fun DependencyHandler.androidTestImplementation(value: String) {
    add("androidTestImplementation", value)
}

fun DependencyHandler.androidTestImplementation(vararg values: String) {
    values.forEach {
        androidTestImplementation(it)
    }
}

fun DependencyHandler.projectModule(value: String) {
    add("implementation", project(":$value"))
}

fun DependencyHandler.projectModule(vararg values: String) {
    values.forEach {
        projectModule(it)
    }
}