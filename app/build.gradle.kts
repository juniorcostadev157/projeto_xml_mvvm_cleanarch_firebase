plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    jacoco
    id("com.google.gms.google-services")
}



android {
    namespace = "com.junior.projetomvvmcleanxml"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.junior.projetomvvmcleanxml"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            enableUnitTestCoverage = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding{
        enable = true
    }

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

        val debugTree = fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/debug")) {
            exclude(fileFilter)
        }

        classDirectories.setFrom(debugTree)
        sourceDirectories.setFrom(files("src/main/java"))
        executionData.setFrom(fileTree(layout.buildDirectory) {
            include("jacoco/testDebugUnitTest.exec")
        })

    }


    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)


        // Firebase BOM to manage versions
        implementation(platform(libs.firebase.bom))

        // Firebase Dependencies
        implementation(libs.firebase.analytics)
        implementation(libs.firebase.auth.ktx)
        implementation(libs.firebase.messaging.ktx)
        implementation(libs.firebase.storage.ktx)
        implementation(libs.firebase.firestore.ktx)

        // viewModelScope
        implementation (libs.androidx.lifecycle.viewmodelKtx)
        implementation (libs.androidx.lifecycle.livedataKtx)
    }
}