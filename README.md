# KMP Compose UI App using Google's Room for iOS and Android

How to use Google Room database (loved by Android devs) in Pure Compose UI KMP apps for iOS and Android (No Viewmodels!)

[<img src= "./screenshots/ios.png" width="200">]()
[<img src= "./screenshots/android.png" width="200">]()

Youtube Video exmplainer:

[<img src="https://github.com/realityexpander/KMP_Room/assets/5157474/5ff980a9-11c5-4d9e-9546-745ccb0006d7">](https://youtu.be/2E-3FDRPmYI)

Room for Kotlin Multi-Platform: https://developer.android.com/kotlin/multiplatform/room#setting-dependencies

Based on this repo: https://github.com/android/kotlin-multiplatform-samples/tree/main/Fruitties

## Original Document:
Project generated from this template (iOS & Android, Shared UI): https://kmp.jetbrains.com/

This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
