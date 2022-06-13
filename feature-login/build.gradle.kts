plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.alaksion.feature_login"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(projects.coreUi)
    implementation(projects.firebase.authentication)
    implementation(projects.navigation)
    implementation(projects.utils.injection)

    implementation(libs.compose.navigation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.bundles.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.kodein.core)
    implementation(libs.kodein.compose)
    implementation(libs.coroutines.core)

    testImplementation(libs.junit)

    debugImplementation(libs.bundles.compose.debug)
}