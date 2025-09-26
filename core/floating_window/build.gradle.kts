plugins {
    alias(libs.plugins.samples.android.library)
    alias(libs.plugins.samples.android.library.compose)
}

android {
    namespace = "com.doanhtv.floating_window"
}

dependencies {}

tasks {
    register("Run floating window")
}