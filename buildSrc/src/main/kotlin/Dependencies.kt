object BuildPlugins {
    object Versions {
        const val androidGradlePlugin = "7.0.1"
        const val kotlin = "1.5.21"
        const val benManes = "0.39.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val benManesVersionPlugin = "com.github.ben-manes.versions"

    const val applicationId = "com.talentsarena.movieapp"

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "android"

    const val kotlinKapt = "kotlin-kapt"
}

object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val target = 30
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.2.0"
        const val material = "1.4.0"
        const val recyclerView = "1.2.1"
        const val swipeRefreshLayout = "1.1.0"
        const val constraintLayout = "2.0.4"
        const val ktx = "1.3.2"
        const val coroutines = "1.5.1"
        const val lifecycleExtension = "2.2.0"
        const val lifecycle = "2.3.0"
        const val activityKtx = "1.3.1"
        const val fragmentKtx = "1.3.6"
        const val okhttp = "4.9.1"
        const val okLog = "2.3.0"
        const val timber = "5.0.1"
        const val retrofit = "2.9.0"
        const val moshi = "1.12.0"
        const val room = "2.2.5"
        const val cardView = "1.0.0"
        const val coil = "1.3.2"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${BuildPlugins.Versions.kotlin}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"

    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"

    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtension}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val okLog = "com.github.simonpercic:oklog3:${Versions.okLog}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiKotlinCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"

    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.2"
        const val rules = "1.3.0"
        const val runner = "1.3.0"
        const val jUnit = "1.1.3"
        const val espresso = "3.4.0"
        const val mockk = "1.10.6"
        const val coroutines = "1.4.2"
        const val googleTruth = "1.1.2"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val jUnit = "androidx.test.ext:junit-ktx:${Versions.jUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"
}

object InstrumentedTestDependencies {
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Projects {
    const val core = ":core"
    const val dataSource = ":datasource"
    const val movieFeature = ":feature:feature-movie"
    const val movieUi = ":ui:ui-movie"
}