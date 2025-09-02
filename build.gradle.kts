// Top-level build file where you add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dokka) apply false
}

// Root-level JaCoCo task to generate coverage for all modules
tasks.register("jacocoRootReport") {
    dependsOn(
        ":app:jacocoTestReport",
        ":domain:jacocoTestReport",
        ":data:jacocoTestReport",
        ":presentation:jacocoTestReport"
    )
    
    description = "Generate JaCoCo coverage reports for all modules"
    group = "verification"
}