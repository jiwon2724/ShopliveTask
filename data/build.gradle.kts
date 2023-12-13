import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.0"

}

android {
    namespace = "jiwondev.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", getApiKey("base_url"))
        buildConfigField("String", "PUBLIC_KEY", getApiKey("public_key"))
        buildConfigField("String", "PRIVATE_KEY", getApiKey("private_key"))
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
//    implementation("androidx.core:core-ktx:1.8.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.squareup.okhttp3)
    implementation(libs.serialization.converter)
    implementation(libs.serialization.json)
    implementation(libs.squareup.gson)

    implementation(project(":domain"))
}

fun getApiKey(propertyKey: String) = gradleLocalProperties(rootDir).getProperty(propertyKey)