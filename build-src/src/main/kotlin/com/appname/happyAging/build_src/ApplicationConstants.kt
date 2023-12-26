package com.appname.happyAging.build_src

import org.gradle.api.JavaVersion

internal object ApplicationConstants {
    const val minSdk = 21
    const val targetSdk = 34
    const val compileSdk = 34
    const val versionCode = 1
    const val versionName = "1.0"
    val javaVersion = JavaVersion.VERSION_17
}