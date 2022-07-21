plugins {
    id ("com.android.application") version "7.1.2" apply false
    id ("com.android.library") version "7.1.2" apply false
    id ("org.jetbrains.kotlin.android") version libs.versions.kotlin.get() apply false
    id("org.jetbrains.kotlin.jvm") version libs.versions.kotlin.get() apply false
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.13")
    }
}
