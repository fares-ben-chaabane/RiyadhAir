plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
}

android {
    namespace = "fr.benchaabane.riyadhair"
    compileSdk = 36

    defaultConfig {
        applicationId = "fr.benchaabane.riyadhair"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isTestCoverageEnabled = true
        }
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

    buildFeatures {
        compose = true
    }
    
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
            excludes += "META-INF/androidx/room/room-compiler-processing/LICENSE.txt"
            excludes += "META-INF/androidx/room/room-compiler-processing/NOTICE.txt"
            excludes += "META-INF/androidx/room/room-compiler-processing/DEPENDENCIES"
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
        "**/*Test*.*",
        "android/**/*.*"
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
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.window.manager)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)
    
    implementation(libs.serialization.json)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    
    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Camera + ML
    implementation(libs.bundles.camera)
    implementation(libs.mlkit.text)

    // Filament
    implementation(libs.filament.android)
    implementation(libs.filament.utils)

    // Modules
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":design_system"))
    implementation(project(":core"))

    testImplementation(libs.bundles.test)
    testImplementation(libs.konsist)

    androidTestImplementation(libs.androidx.junit)
}

// Dokka
dokka {
    moduleName.set("RiyadhAir AppModule")
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(false)
    }
    pluginsConfiguration.html {
        footerMessage.set("(c) Riyadh Air")
    }
    dependencies {
        dokka(project(":presentation:"))
        dokka(project(":domain:"))
        dokka(project(":data:"))
        dokka(project(":design_system:"))
        dokka(project(":core:"))
    }
}