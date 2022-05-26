plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.alaksion.core_ui"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.core.get()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)

}