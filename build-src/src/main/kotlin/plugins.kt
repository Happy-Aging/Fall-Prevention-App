import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object PluginEnum {
    const val KotlinCore = "kotlin"
    const val KotlinKapt = "kotlin-kapt"
    const val KotlinAndroid = "kotlin-android"
    const val KotlinParcelize = "kotlin-parcelize"

    const val AndroidApplication = "com.android.application"
    const val AndroidLibrary = "com.android.library"

    const val JavaLibrary = "java-library"
}

val PluginDependenciesSpec.`kotlin-kapt`: PluginDependencySpec
    get() = id(PluginEnum.KotlinKapt)

val PluginDependenciesSpec.`kotlin-android`: PluginDependencySpec
    get() = id(PluginEnum.KotlinAndroid)

val PluginDependenciesSpec.`kotlin-parcelize`: PluginDependencySpec
    get() = id(PluginEnum.KotlinParcelize)

val PluginDependenciesSpec.`android-application`: PluginDependencySpec
    get() = id(PluginEnum.AndroidApplication)

val PluginDependenciesSpec.`android-library`: PluginDependencySpec
    get() = id(PluginEnum.AndroidLibrary)

val PluginDependenciesSpec.`java-library`: PluginDependencySpec
    get() = id(PluginEnum.JavaLibrary)