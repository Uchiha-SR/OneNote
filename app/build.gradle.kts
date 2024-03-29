plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.onenote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.onenote"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("com.airbnb.android:lottie-compose:6.1.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.0-alpha08")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.5")
    implementation("javax.inject:javax.inject:1")
    implementation("androidx.biometric:biometric:1.2.0-alpha05")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.wear.compose:compose-material3:1.0.0-alpha15")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("androidx.core:core-splashscreen:1.0.0-beta02")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    implementation("me.saket.telephoto:zoomable-image-coil:0.6.2")

    implementation ("androidx.navigation:navigation-compose:2.4.0-alpha09")


    implementation ("androidx.biometric:biometric-ktx:1.2.0-alpha05")


}