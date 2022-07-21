plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.gms.google-services")
}

android {
    namespace = "br.com.alaksion.noteapp"
    compileSdk = 32

    defaultConfig {
        applicationId = "br.com.alaksion.noteapp"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    
}

dependencies {
    implementation(projects.coreUi)
    implementation(projects.firebase.authentication)
    implementation(projects.featureLogin)
    implementation(projects.featureHome)
    implementation(projects.navigation)
    implementation(projects.utils.injection)

    implementation(libs.compose.navigation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.compose.ui)
    implementation(libs.compose.activity)

    implementation(libs.kodein.core)
    implementation(libs.kodein.compose)

    testImplementation(libs.junit)
    debugImplementation(libs.bundles.compose.debug)
}