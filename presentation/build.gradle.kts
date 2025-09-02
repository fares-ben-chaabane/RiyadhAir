plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
}

android {
    namespace = "fr.benchaabane.riyadhair.presentation"
    compileSdk = 36

    defaultConfig { minSdk = 26 }
    
    buildTypes {
        debug {
            isTestCoverageEnabled = true
        }
    }
    
    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    
    configurations.all {
        exclude(group = "com.intellij", module = "annotations")
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/gradle/incremental.annotation.processors"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module"
        }
    }
}

// JaCoCo configuration
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")
    
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
    
    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*"
    )
    
    val debugTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    
    val mainSrc = "${project.projectDir}/src/main/java"
    
    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))
    executionData.setFrom(fileTree(project.buildDir) {
        include("/outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
    })
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":design_system"))
    implementation(project(":core"))

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.window.manager)
    implementation(libs.coil.compose)

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.hilt)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    
    implementation(libs.bundles.camera)
    implementation(libs.mlkit.text)
    
    // Paging 3
    implementation(libs.bundles.paging)
    
    // Testing
    testImplementation(libs.bundles.test)
    testImplementation(libs.turbine)
}

// Dokka
dokka {
    moduleName.set("RiyadhAir PresentationLayer")
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(false)
    }
    pluginsConfiguration.html {
        footerMessage.set("(c) Riyadh Air")
    }
}


