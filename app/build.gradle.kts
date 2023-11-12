plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.flickerphotosapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flickerphotosapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_URL", "\"https://www.flickr.com/\"")
            buildConfigField("String", "IMAGES_BASE_URL", "\"https://farm66.staticflickr.com/65535/\"")
            buildConfigField("String", "API_KEY", "\"d17378e37e555ebef55ab86c4180e8dc\"")
            buildConfigField("String", "APP_SECRET", "\"77df5d88-f297-4e82-95b7-dc2843e285a8\"")
        }
        release {
            buildConfigField("String", "API_URL", "\"https://www.flickr.com/\"")
            buildConfigField("String", "IMAGES_BASE_URL", "\"https://farm66.staticflickr.com/65535/\"")
            buildConfigField("String", "API_KEY", "\"d17378e37e555ebef55ab86c4180e8dc\"")
            buildConfigField("String", "APP_SECRET", "\"77df5d88-f297-4e82-95b7-dc2843e285a8\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Coroutines
    val coroutinesVersion = "1.7.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    //Retrofit
    val retrofitVersion = "2.9.0"
    val loggingInterceptorVersion = "4.11.0"
    val gsonVersion = "2.10"
    val coroutinesConverterVersion = "0.9.2"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$coroutinesConverterVersion")

    //Fragment
    val fragmentKTXVersion = "1.6.2"
    implementation("androidx.fragment:fragment-ktx:$fragmentKTXVersion")

    //Navigation
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //timber
    val timberVersion = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timberVersion")

    //hilt
    val hiltVersion = "2.47"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    //Picasso
    val picassoVersion = "2.8"
    implementation("com.squareup.picasso:picasso:$picassoVersion")

    // Room components
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    // Paging
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    //Testing
    val coroutinesTestVersion = "1.7.1"
    val jUnitVersion = "4.13.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion")
    implementation("junit:junit:$jUnitVersion")

    val appCenterSdkVersion = "4.4.5"
    implementation("com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}")
    implementation("com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}")

    val mockitoInlineVersion = "4.8.0"
    val mockitoKotlinVersion = "4.0.0"
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion")
    testImplementation("org.mockito:mockito-inline:$mockitoInlineVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

}