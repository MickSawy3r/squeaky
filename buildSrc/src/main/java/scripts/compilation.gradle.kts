package scripts

plugins {
    id("com.android.application") apply false
    id("kotlin-android") apply false
}

android {
    packagingOptions {
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    testOptions {
        animationsDisabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
