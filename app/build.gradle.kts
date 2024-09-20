import com.android.builder.core.apiVersionFromString

plugins {
    // Supplied by New Project template
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // Added by Hollarhype
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    kotlin(libs.plugins.kotlin.serialization.get().pluginId) version libs.versions.kotlinJvm
}

android {
    namespace = "com.codepunk.hollarhype"
    compileSdk = 35
    compileSdkPreview = "VanillaIceCream"

    defaultConfig {
        applicationId = "com.codepunk.hollarhype"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            type = "String",
            name = "APPLICATION_NAME",
            value = "\"hollarhype\""
        )

        buildConfigField(
            type = "long",
            name = "OK_HTTP_CLIENT_CACHE_SIZE",
            value = "10 * 1024 * 1024"
        )
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

    flavorDimensions += "version"

    productFlavors {
        create("production") {
            dimension = "version"
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api.hollarhype.com/\""
            )
        }
        create("staging") {
            dimension = "version"
            applicationIdSuffix = ".staging"
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api-stg.hollarhype.com/\""
            )
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Supplied by New Project template
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Added by Hollarhype
    // Material 3 Adaptive
    implementation(libs.material3.adaptive)
    implementation(libs.material3.adaptive.layout)
    implementation(libs.material3.adaptive.navigation)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // Serialization
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.converter)

    // Compose navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    // Arrow
    implementation(libs.arrow.core)
    implementation(libs.arrow.eval)
    implementation(libs.arrow.core.retrofit)
    implementation(libs.arrow.fx.coroutines)

    // Okhttp
    implementation(libs.okhttp)

    // Retrofit
    implementation(libs.retrofit)

    // Room
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.paging)

    // Credentials
    implementation(libs.credentials.play.services.auth)
    implementation(libs.credentials)

    // Accompanist
    implementation(libs.accompanist)

    // Google Phone Number library
    implementation(libs.phonenumber)
}
