object Dependencies {

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleLivedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
        }
    }

    object Google {
        const val material =
            "com.google.android.material:material:${Versions.material}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object Moshi {
        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    }

    object Hilt {
        const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
        const val composeNavigation =
            "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"
    }

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"

    object Kotlin {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
        const val testJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }


    object AndroidX {
        const val core = "androidx.test:core:${Versions.testingCore}"
        const val archCore = "androidx.arch.core:core-testing:${Versions.testingArchCore}"

        object Espresso {
            const val core = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
            const val contributor =
                "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
        }

        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
        const val testRules = "androidx.test:rules:${Versions.testRunner}"

        const val junit = "androidx.test.ext:junit:${Versions.xJunit}"
    }

    object Google {
        const val truth = "com.google.truth:truth:${Versions.truth}"
    }

    object Mockito {
        const val inline = "org.mockito:mockito-inline:${Versions.mockito}"
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    }

    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}


object Versions {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30

    // Kotlin
    const val kotlin = "1.5.21"
    const val coroutines = "1.5.1"

    // AndroidX
    const val coreKtx = "1.6.0"
    const val lifecycle = "2.3.1"
    const val constraintLayout = "2.1.0"
    const val fragment = "1.3.6"
    const val navigation = "2.3.5"
    const val compose = "1.0.1"
    const val composeConstraintLayout = "1.0.0-beta02"

    // Google
    const val material = "1.4.0"
    const val accompanist = "0.18.0"

    // Networking
    const val okhttp = "4.9.1"
    const val retrofit = "2.9.0"
    const val moshi = "1.12.0"

    // Hilt
    const val hilt = "2.38.1"
    const val hiltViewModel = "1.0.0-alpha03"
    const val hiltComposeNavigation = "1.0.0-alpha03"

    const val coil = "1.3.2"

    // Test
    const val junit = "4.13.2"
    const val testingCore = "1.4.0"
    const val coroutinesTest = "1.4.2"
    const val mockito = "3.11.2"
    const val mockitoKotlin = "3.2.0"
    const val testingArchCore = "2.1.0"
    const val truth = "1.1.3"
    const val espressoCore = "3.4.0"
    const val testRunner = "1.4.0"
    const val xJunit = "1.1.2"
}