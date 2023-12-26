package com.appname.happyAging.build_src

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project


internal fun Project.configureApplication(extension: CommonExtension<*, *, *, *, *>) {

    extension.apply {
        compileSdk = ApplicationConstants.compileSdk

        defaultConfig {
            minSdk = ApplicationConstants.minSdk
        }

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin/")
        }

        compileOptions {
            sourceCompatibility = ApplicationConstants.javaVersion
            targetCompatibility = ApplicationConstants.javaVersion
        }

        lint {
            checkTestSources = true
        }

        dependencies.add("detektPlugins", libs.findLibrary("detekt-plugin-formatting").get().get())
    }
}