plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.yasintanriverdi.disneycharacters"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        forEach {
         it. buildConfigField(
             type = "String",
             name = "BASE_URL",
             value = "\"https://api.disneyapi.dev/\""
         )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        animationsDisabled = true
    }
}

dependencies {
    // Kotlin
    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.coroutines)

    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUiKtx)
    implementation(Dependencies.AndroidX.lifecycleViewModel)
    implementation(Dependencies.AndroidX.lifecycleLivedata)
    kapt(Dependencies.AndroidX.lifecycleCompiler)

    // Compose
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.constraintLayout)

    // Google
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.insets)
    implementation(Dependencies.Google.flowLayout)

    // Hilt
    implementation(Dependencies.Hilt.core)
    implementation(Dependencies.Hilt.viewModel)
    implementation(Dependencies.Hilt.composeNavigation)
    kapt(Dependencies.Hilt.compiler)

    // OkHttp
    implementation(Dependencies.OkHttp.core)
    implementation(Dependencies.OkHttp.loggingInterceptor)

    // Retrofit
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.moshiConverter)

    // Moshi
    implementation(Dependencies.Moshi.core)
    implementation(Dependencies.Moshi.adapters)
    implementation(Dependencies.Moshi.kotlin)

    implementation(Dependencies.coil)

    // Test
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.Kotlin.coroutines)
    testImplementation(TestDependencies.Kotlin.testJUnit)
    testImplementation(TestDependencies.Mockito.inline)
    testImplementation(TestDependencies.Mockito.kotlin)
    testImplementation(TestDependencies.AndroidX.core)
    testImplementation(TestDependencies.AndroidX.archCore)
    testImplementation(TestDependencies.Google.truth)
    testImplementation(TestDependencies.mockWebServer)

    androidTestImplementation(TestDependencies.AndroidX.junit)
    androidTestImplementation(TestDependencies.AndroidX.core)
    androidTestImplementation(TestDependencies.AndroidX.Espresso.core)
    androidTestImplementation(TestDependencies.AndroidX.Espresso.contributor)
    androidTestImplementation(TestDependencies.AndroidX.testRunner)
    androidTestImplementation(TestDependencies.AndroidX.testRules)
}