plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinKapt)
    kotlin(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = AndroidSdk.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(Libraries.kotlinStdLib)
    api(Libraries.appCompat)
    api(Libraries.ktxCore)

    api(Libraries.activityKtx)
    api(Libraries.fragmentKtx)

    implementation(Libraries.recyclerView)

    implementation(Libraries.retrofit)
}