buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:4.2.0-rc01")
    }
}
group = "com.jetbrains"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
