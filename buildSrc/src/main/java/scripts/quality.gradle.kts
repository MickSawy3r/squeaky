package scripts

import io.gitlab.arturbosch.detekt.Detekt
import scripts.Variants_gradle.*

plugins {
    id("com.android.application") apply false
    id("jacoco")
    id("io.gitlab.arturbosch.detekt")
}

android {
    lint {
        isQuiet = true
        isAbortOnError = false
        isIgnoreWarnings = true
        disable("InvalidPackage")           //Some libraries have issues with this.
        disable("OldTargetApi")             //Lint gives this warning related to SDK Beta.
        disable("IconDensities")            //For testing purpose. This is safe to remove.
        disable("IconMissingDensityFolder") //For testing purpose. This is safe to remove.
    }
}

val jacocoReport by tasks.registering(JacocoReport::class) {
    group = "Quality"
    description = "Report code coverage on tests within the Android codebase."
    dependsOn("test${Default.BUILD_VARIANT}UnitTest")

    val buildVariantClassPath = "${Default.BUILD_FLAVOR}${Default.BUILD_TYPE.capitalize()}"
    val outputDir = "$buildDir/testCoverage"

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }

    classDirectories.setFrom(fileTree(buildDir) {
        include(
            "**/classes/**/main/**",
            "**/intermediates/classes/$buildVariantClassPath/**",
            "**/intermediates/javac/$buildVariantClassPath/*/classes/**",
            "**/tmp/kotlin-classes/$buildVariantClassPath/**")
        exclude(
            "**/R.class",
            "**/R\$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/Manifest$*.class",
            "**/*Test*.*",
            "**/Injector.*",
            "android/**/*.*",
            "**/*\$Lambda$*.*",
            "**/*\$inlined$*.*",
            "**/di/*.*",
            "**/*Database.*",
            "**/*Response.*",
            "**/*Application.*",
            "**/*Entity.*")
    }
    )
    sourceDirectories.setFrom(fileTree(projectDir) {
        include("src/main/java/**", "src/main/kotlin/**") })
    executionData.setFrom(fileTree(buildDir) {
        include("**/*.exec", "**/*.ec") })

    doLast { println("Code Coverage Report: $outputDir/index.html") }
}

tasks.register("runTestCoverage") {
    group = "Quality"
    description = "Report code coverage on tests within the Android codebase."
    dependsOn(jacocoReport)
}

val detektAll by tasks.registering(Detekt::class) {
    group = "Quality"
    description = "Runs a detekt code analysis on the Android codebase."
    parallel = true
    buildUponDefaultConfig = true

    val outputFile = "$buildDir/staticAnalysis/index.html"

    setSource(files(projectDir))
    config.setFrom("$rootDir/config/detekt/detekt.yml")

    include("**/*.kt")
    exclude("**/*.kts", "*/build/*", "/buildSrc")

    reports {
        html.enabled = true
        html.destination = file(outputFile)
        xml.enabled = false
        txt.enabled = false
    }

    val reportFile = "Static Analysis Report: $outputFile \n"
    doFirst { println(reportFile) }
    doLast { println(reportFile) }
}

tasks.register("runStaticAnalysis") {
    description = "Run static analysis on the Android codebase."
    dependsOn(detektAll)
}
