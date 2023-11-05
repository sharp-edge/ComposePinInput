plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.sharpedge.pintextfield"
    compileSdk = 33

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    //implementation("androidx.core:core-ktx:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    // Include only if you use graphics APIs from this library
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    //debugImplementation("androidx.compose.ui:ui-tooling-preview")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

//tasks.register("printComponents") {
//    doLast {
//        components.forEach {
//            println(it.name)
//        }
//    }
//}


// Place this at the end of your build.gradle.kts file
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.sharpedge"
                artifactId = "ComposePinInput"
                version = "1.0.0"

                // This should match the output of your printComponents task
                from(components["release"])
            }
        }
        repositories {
            // Comment out the local maven repository before pushing to remote
//            maven {
//                url = uri("file:///${rootProject.buildDir}/localMaven")
//            }
        }
    }
}