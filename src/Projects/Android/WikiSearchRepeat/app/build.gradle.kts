plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "org.csystem.android.app.wikisearchrepeat"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.csystem.android.app.wikisearchrepeat"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    dataBinding.enable = true
}

dependencies {
    implementation(project(":RepositoryLib"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
}

kapt {
    correctErrorTypes = true
}