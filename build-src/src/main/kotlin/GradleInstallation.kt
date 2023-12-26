import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.appname.happyAging.build_src.ApplicationConstants
import com.appname.happyAging.build_src.applyPlugins
import com.appname.happyAging.build_src.configureApplication
import com.appname.happyAging.build_src.libs
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

interface GradleInstallationScope {
    fun android(block: BaseAppModuleExtension.() -> Unit = {})
    fun library(block: LibraryExtension.() -> Unit = {})
    fun library(namespace: String)
    fun jvm()
    fun hilt()
}

private class GradleInstallationScopeImpl(private val project: Project) : GradleInstallationScope {
    override fun android(block: BaseAppModuleExtension.() -> Unit) {
        with(project) {
            applyPlugins(
                PluginEnum.AndroidApplication,
                PluginEnum.KotlinAndroid,
            )

            extensions.configure<BaseAppModuleExtension> {
                configureApplication(this)

                buildFeatures {
                    buildConfig = false
                }

                defaultConfig {
                    targetSdk = ApplicationConstants.targetSdk
                    versionName = ApplicationConstants.versionName
                    versionCode = ApplicationConstants.versionCode
                }

                block()
            }
        }
    }

    override fun library(block: LibraryExtension.() -> Unit) {
        with(project) {
            applyPlugins(
                PluginEnum.AndroidLibrary,
                PluginEnum.KotlinAndroid,
            )

            extensions.configure<LibraryExtension> {
                configureApplication(this)

                buildFeatures {
                    buildConfig = false
                }

                defaultConfig.apply {
                    targetSdk = ApplicationConstants.targetSdk
                }

                block()
            }
        }
    }

    override fun library(namespace: String) {
        library {
            this.namespace = namespace
        }
    }

    override fun jvm() {
        with(project) {
            applyPlugins(
                PluginEnum.JavaLibrary,
                PluginEnum.KotlinCore,
            )

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = ApplicationConstants.javaVersion
                targetCompatibility = ApplicationConstants.javaVersion
            }
        }
    }



    override fun hilt() {
        with(project) {
            applyPlugins(
                PluginEnum.KotlinKapt,
                libs.findPlugin("di-hilt").get().get().pluginId,
            )

            dependencies {
                add("implementation", libs.findLibrary("di-hilt-core").get())
                add("kapt", libs.findLibrary("di-hilt-compiler").get())
            }
        }
    }
}

object GradleInstallation {
    fun with(project: Project, block: GradleInstallationScope.() -> Unit) {
        GradleInstallationScopeImpl(project).block()
    }
}