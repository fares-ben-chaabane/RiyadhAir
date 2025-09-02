plugins {
    alias(libs.plugins.dokka)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "fr.benchaabane.riyadhair.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures { compose = true }
    
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

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.coroutines)
    
    // Compose for adaptive UI
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.window.manager)
    
    // Camera and ML Kit for MRZ
    implementation(libs.bundles.camera)
    implementation(libs.mlkit.text)
}

// Dokka
dokka {
    moduleName.set("RiyadhAir Core")
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(false)
    }
    pluginsConfiguration.html {
        footerMessage.set("(c) Riyadh Air")
    }
}


