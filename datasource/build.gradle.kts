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
            buildConfigField("String", "BASE_URL", "\"${Config.PROD_BASE_URL}\"")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"${Config.STAGING_BASE_URL}\"")
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
    implementation(Libraries.okhttp)
    implementation(Libraries.okhttpUrlConnection)
    implementation(Libraries.okhttpLoggingInterceptor)

    debugImplementation(Libraries.okLog)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshi)

    implementation(Libraries.moshiKotlin)
    kapt(Libraries.moshiKotlinCodeGen)

    api(Libraries.roomRuntime)
    api(Libraries.roomKtx)
    kapt(Libraries.roomKapt)
}