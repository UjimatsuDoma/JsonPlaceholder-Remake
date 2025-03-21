# Changelog

## March 21, 2025

1. Bump AGP version to 7.4, Gradle to 7.5 and Gradle JDK version to 17
2. Bumped Kotlin version to 1.9.10 to resolve exceptions
3. Run AGP Upgrade Assistant to bump AGP version to 8.9.0
4. Bump Kotlin version to 2.1.0, replace Kotlin Android Extension with ViewBinding in gradle file, replace `kotlin-stdlib-jdk7` with `kotlin-stdlib` and upgrade `compileSdkVersion` to `35`
5. Upgrade Koin dependency, bump version to 4.0.2
6. Migrate Android Support Library to AndroidX - remove old dependencies, add corresponding properties in `gradle.properties`
   1. `android.useAndroidX=true`
   2. `android.enableJetifier=true`
7. Fix DI modules and methods
   1. in `JsonApp`: the `startKoin` method
   2. in `appModule.kt`: reimport the `module` method
8. Fix layout-related code - upgrade to view binding in Kotlin code, replace old layouts and components with AndroidX ones in XML
9. Bump `minSdkVersion` to 21 to fix exception
10. Modify `AndroidManifest.xml` to fix "launcher activity must be exported" error
11. Add `compileOptions` block in app-level gradle file to resolve "inconsistent jdk" error
12. Add `kotlin-parcelize` plugin and upgrade dependency in Kotlin code
13. Fix other errors
    1. `@LayoutRes` import in `View.kt`
    2. `onSendCommentClick` `when`-clause error in `ItemDetailPresenter`
14. Fix: `onCreateViewHolder` invoke the three-argument overload to fix incorrect list item size