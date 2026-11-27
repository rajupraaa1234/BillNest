plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.mobile.sdk.compose"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.compose.ui)
    api(libs.compose.material3)
    api("androidx.compose.material:material-icons-core")
    api("androidx.compose.material:material-icons-extended")
    api(libs.compose.ui.tooling.preview)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.activity.compose)
    api(libs.navigation.compose)
    api(libs.lifecycle.viewmodel.compose)
    debugApi(libs.compose.ui.tooling)
}
