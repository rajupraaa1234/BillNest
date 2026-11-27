plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mobile.features.onboarding"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}
