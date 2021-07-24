object Kotlin {
    const val standardLibrary = "1.4.10"
    const val coroutines = "1.3.9"
}

object AndroidSdk {
    const val min = 23
    const val compile = 30
    const val target = compile
}

object AndroidClient {
    const val appId = "com.ticketswap.assessment"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "4.1.0"
        const val gradleVersion = "6.7"
        const val hilt = "2.37"
        const val realm = "10.6.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
    const val realmGradlePlugin = "io.realm:realm-gradle-plugin:${Versions.realm}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val androidHilt = "dagger.hilt.android.plugin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinParcelize = "kotlin-parcelize"
    const val realm = "realm-android"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
}

object ScriptPlugins {
    const val infrastructure = "scripts.infrastructure"
    const val variants = "scripts.variants"
    const val quality = "scripts.quality"
    const val compilation = "scripts.compilation"
}

object Libraries {
    private object Versions {
        const val hilt = BuildPlugins.Versions.hilt
        const val appCompat = "1.2.0"
        const val constraintLayout = "2.0.2"
        const val recyclerView = "1.1.0"
        const val cardView = "1.0.0"
        const val material = "1.1.0"
        const val lifecycle = "2.2.0"
        const val lifecycleExtensions = "2.1.0"
        const val annotations = "1.1.0"
        const val ktx = "1.3.5"
        const val coreKtx = "1.6.0"
        const val glide = "4.11.0"
        const val retrofit = "2.9.0"
        const val okHttp = "4.9.0"
        const val retrofitRxAdapter = "3.0.0"
        const val rxJava = "3.0.13"
        const val rxAndroid = "3.0.0"
        const val spotifyVersion = "1.2.5"
        const val swipeRefreshLayout = "1.1.0"
        const val room = "2.3.0"
        const val activityKtx = "1.2.3"
        const val moshi = "1.12.0"
        const val picasso = "2.71828"
        const val lottie = "3.7.2"
    }

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomAnnotationProcessor = "androidx.room:room-compiler:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxAdapter = "androidx.room:room-rxjava3:${Versions.room}"

    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.standardLibrary}"
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidAnnotations = "androidx.annotation:annotation:${Versions.annotations}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    const val retrofit = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val moshiAdapter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val okHttp =
        "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxRetrofitAdapter =
        "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.retrofitRxAdapter}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.ktx}"
    const val spotify = "com.spotify.android:auth:${Versions.spotifyVersion}"
}

object TestLibraries {
    object Versions {
        const val junit4 = "4.13.2"
        const val junit5 = "5.7.1"
        const val robolectric = "4.4"
        const val mockito = "3.9.0"
        const val mockitoKotlin = "3.2.0"
        const val kluent = "1.68"
        const val testRunner = "1.1.0"
        const val espressoCore = "3.2.0"
        const val espressoIntents = "3.1.0"
        const val testExtensions = "1.1.1"
        const val testRules = "1.1.0"
        const val coroutinesTest = "1.5.0"
        const val hiltTesting = BuildPlugins.Versions.hilt
        const val okHttp = "4.9.0"
        const val coreTesting = "2.1.0"
        const val fragmentTesting = "1.3.5"
    }

    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoIntents =
        "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtensions}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltTesting}"

    const val jUnitJupyter = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val mockitoJUnitJupyter = "org.mockito:mockito-junit-jupiter:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val okHttpMockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
}

object RuntimeLibraries {
    private object Versions {
        const val kotlinReflect = "1.5.21"
    }

    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinReflect}"
}

object TestRuntimeLibraries {
    const val jUnitJupyter = "org.junit.jupiter:junit-jupiter-api:${TestLibraries.Versions.junit5}"
}

object DevLibraries {
    private object Versions {
        const val leakCanary = "2.5"
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}
