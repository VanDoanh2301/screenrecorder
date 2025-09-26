plugins {
    alias(libs.plugins.samples.android.library)
    alias(libs.plugins.samples.android.library.compose)
}

android {
    namespace = "com.aihomework.floating_window"
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockk)
    testImplementation(libs.core.ktx)
}
