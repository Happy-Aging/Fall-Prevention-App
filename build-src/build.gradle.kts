@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin{
    plugins{
        register("androidHilt") {
            id = "happyAging.android.hilt"
            implementationClass = "com.appname.happyAging.build_src.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "happyAging.kotlin.hilt"
            implementationClass = "com.appname.happyAging.build_src.HiltKotlinPlugin"
        }
    }
}