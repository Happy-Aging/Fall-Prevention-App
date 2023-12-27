import io.netty.util.ReferenceCountUtil.release
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.appname.happyAging"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.appname.happyAging"
        targetSdk = 34
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.android.compose)
    implementation(libs.bundles.android.ktx)
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.bundles.android.compose.debug)

    //hlit
    implementation(libs.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.bundles.network)
    implementation(libs.splashscreen)

    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core"))
}


kapt {
    correctErrorTypes = true
}