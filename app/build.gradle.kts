plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinKapt)
    kotlin(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = BuildPlugins.applicationId
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"${Config.PROD_BASE_URL}\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"${Config.IMAGE_BASE_URL}\"")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"${Config.STAGING_BASE_URL}\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"${Config.IMAGE_BASE_URL}\"")
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
    implementation(project(Projects.movieUi))
    implementation(project(Projects.core))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.material)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)

    implementation(Libraries.swipeRefreshLayout)
    implementation(Libraries.recyclerView)
    implementation(Libraries.cardView)

    implementation(Libraries.lifecycleExtension)
    implementation(Libraries.lifecycleViewModel)
    implementation(Libraries.lifecycleRuntime)

    implementation(Libraries.fragmentKtx)

    implementation(Libraries.okLog)
    implementation(Libraries.timber)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshi)

    implementation(Libraries.okhttp)
    implementation(Libraries.okhttpUrlConnection)
    implementation(Libraries.okhttpLoggingInterceptor)

    api(Libraries.roomRuntime)
    api(Libraries.roomKtx)
    kapt(Libraries.roomKapt)

    implementation(Libraries.moshiKotlin)
    kapt(Libraries.moshiKotlinCodeGen)

    implementation(Libraries.coil)
}