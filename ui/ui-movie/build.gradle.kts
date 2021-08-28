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
        testInstrumentationRunner = InstrumentedTestDependencies.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "IMAGE_BASE_URL", "\"${Config.IMAGE_BASE_URL}\"")
            buildConfigField("String", "API_KEY", "\"${Config.API_KEY}\"")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "IMAGE_BASE_URL", "\"${Config.IMAGE_BASE_URL}\"")
            buildConfigField("String", "API_KEY", "\"${Config.API_KEY}\"")
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
    implementation(project(Projects.core))
    implementation(project(Projects.movieFeature))

    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)
    implementation(Libraries.recyclerView)
    implementation(Libraries.swipeRefreshLayout)
    implementation(Libraries.cardView)

    implementation(Libraries.fragmentKtx)

    implementation(Libraries.lifecycleExtension)
    implementation(Libraries.lifecycleViewModel)
    implementation(Libraries.lifecycleRuntime)

    implementation(Libraries.coil)

    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.googleTruth)

    androidTestImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.runner)
    androidTestImplementation(TestLibraries.rules)
    androidTestImplementation(TestLibraries.espresso)
    androidTestImplementation(TestLibraries.espressoContrib)
}