package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final Material3LibraryAccessors laccForMaterial3LibraryAccessors = new Material3LibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(providers, config);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers) {
        super(config, providers);
    }

        /**
         * Creates a dependency provider for androidxactivity (androidx.activity:activity-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAndroidxactivity() { return create("androidxactivity"); }

        /**
         * Creates a dependency provider for androidxktx (androidx.core:core-ktx)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAndroidxktx() { return create("androidxktx"); }

        /**
         * Creates a dependency provider for archcoretesting (androidx.arch.core:core-testing)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getArchcoretesting() { return create("archcoretesting"); }

        /**
         * Creates a dependency provider for browser (androidx.browser:browser)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBrowser() { return create("browser"); }

        /**
         * Creates a dependency provider for coilcompose (io.coil-kt:coil-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCoilcompose() { return create("coilcompose"); }

        /**
         * Creates a dependency provider for composebom (androidx.compose:compose-bom)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getComposebom() { return create("composebom"); }

        /**
         * Creates a dependency provider for composematerial3 (androidx.compose.material3:material3)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getComposematerial3() { return create("composematerial3"); }

        /**
         * Creates a dependency provider for composetestjunit4 (androidx.compose.ui:ui-test-junit4)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getComposetestjunit4() { return create("composetestjunit4"); }

        /**
         * Creates a dependency provider for composetoolingpreview (androidx.compose.ui:ui-tooling-preview)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getComposetoolingpreview() { return create("composetoolingpreview"); }

        /**
         * Creates a dependency provider for composeui (androidx.compose.ui:ui)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getComposeui() { return create("composeui"); }

        /**
         * Creates a dependency provider for composeuigraphics (androidx.compose.ui:ui-graphics)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getComposeuigraphics() { return create("composeuigraphics"); }

        /**
         * Creates a dependency provider for convertergson (com.squareup.retrofit2:converter-gson)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConvertergson() { return create("convertergson"); }

        /**
         * Creates a dependency provider for coroutinestest (org.jetbrains.kotlinx:kotlinx-coroutines-test)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCoroutinestest() { return create("coroutinestest"); }

        /**
         * Creates a dependency provider for espressocore (androidx.test.espresso:espresso-core)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getEspressocore() { return create("espressocore"); }

        /**
         * Creates a dependency provider for hiltandroid (com.google.dagger:hilt-android)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltandroid() { return create("hiltandroid"); }

        /**
         * Creates a dependency provider for hiltcompiler (com.google.dagger:hilt-compiler)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltcompiler() { return create("hiltcompiler"); }

        /**
         * Creates a dependency provider for hiltnavigationcompose (androidx.hilt:hilt-navigation-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltnavigationcompose() { return create("hiltnavigationcompose"); }

        /**
         * Creates a dependency provider for junit (junit:junit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() { return create("junit"); }

        /**
         * Creates a dependency provider for junitext (androidx.test.ext:junit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunitext() { return create("junitext"); }

        /**
         * Creates a dependency provider for lifecycleruntimecompose (androidx.lifecycle:lifecycle-runtime-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLifecycleruntimecompose() { return create("lifecycleruntimecompose"); }

        /**
         * Creates a dependency provider for lifecycleviewmodelcompose (androidx.lifecycle:lifecycle-viewmodel-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLifecycleviewmodelcompose() { return create("lifecycleviewmodelcompose"); }

        /**
         * Creates a dependency provider for mockito (org.mockito:mockito-core)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMockito() { return create("mockito"); }

        /**
         * Creates a dependency provider for navigationcompose (androidx.navigation:navigation-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNavigationcompose() { return create("navigationcompose"); }

        /**
         * Creates a dependency provider for navigationtesting (androidx.navigation:navigation-testing)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNavigationtesting() { return create("navigationtesting"); }

        /**
         * Creates a dependency provider for pagingcompose (androidx.paging:paging-compose)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPagingcompose() { return create("pagingcompose"); }

        /**
         * Creates a dependency provider for pagingruntime (androidx.paging:paging-runtime-ktx)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPagingruntime() { return create("pagingruntime"); }

        /**
         * Creates a dependency provider for retrofit (com.squareup.retrofit2:retrofit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRetrofit() { return create("retrofit"); }

        /**
         * Creates a dependency provider for roomcompiler (androidx.room:room-compiler)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRoomcompiler() { return create("roomcompiler"); }

        /**
         * Creates a dependency provider for roomktx (androidx.room:room-ktx)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRoomktx() { return create("roomktx"); }

        /**
         * Creates a dependency provider for roomruntime (androidx.room:room-runtime)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRoomruntime() { return create("roomruntime"); }

        /**
         * Creates a dependency provider for turbine (app.cash.turbine:turbine)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTurbine() { return create("turbine"); }

        /**
         * Creates a dependency provider for uitestjunit4 (androidx.compose.ui:ui-test-junit4)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUitestjunit4() { return create("uitestjunit4"); }

        /**
         * Creates a dependency provider for uitestmanifest (androidx.compose.ui:ui-test-manifest)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUitestmanifest() { return create("uitestmanifest"); }

        /**
         * Creates a dependency provider for uitooling (androidx.compose.ui:ui-tooling)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUitooling() { return create("uitooling"); }

    /**
     * Returns the group of libraries at material3
     */
    public Material3LibraryAccessors getMaterial3() { return laccForMaterial3LibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class Material3LibraryAccessors extends SubDependencyFactory {

        public Material3LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (androidx.compose.material3:material3-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("material3.android"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ActivityVersionAccessors vaccForActivityVersionAccessors = new ActivityVersionAccessors(providers, config);
        private final AndroidVersionAccessors vaccForAndroidVersionAccessors = new AndroidVersionAccessors(providers, config);
        private final ArchVersionAccessors vaccForArchVersionAccessors = new ArchVersionAccessors(providers, config);
        private final ComposeVersionAccessors vaccForComposeVersionAccessors = new ComposeVersionAccessors(providers, config);
        private final ConverterVersionAccessors vaccForConverterVersionAccessors = new ConverterVersionAccessors(providers, config);
        private final CoroutinesVersionAccessors vaccForCoroutinesVersionAccessors = new CoroutinesVersionAccessors(providers, config);
        private final EspressoVersionAccessors vaccForEspressoVersionAccessors = new EspressoVersionAccessors(providers, config);
        private final HiltVersionAccessors vaccForHiltVersionAccessors = new HiltVersionAccessors(providers, config);
        private final JunitVersionAccessors vaccForJunitVersionAccessors = new JunitVersionAccessors(providers, config);
        private final KotlinVersionAccessors vaccForKotlinVersionAccessors = new KotlinVersionAccessors(providers, config);
        private final LifecycleVersionAccessors vaccForLifecycleVersionAccessors = new LifecycleVersionAccessors(providers, config);
        private final Material3VersionAccessors vaccForMaterial3VersionAccessors = new Material3VersionAccessors(providers, config);
        private final NavigationVersionAccessors vaccForNavigationVersionAccessors = new NavigationVersionAccessors(providers, config);
        private final PagingVersionAccessors vaccForPagingVersionAccessors = new PagingVersionAccessors(providers, config);
        private final RoomVersionAccessors vaccForRoomVersionAccessors = new RoomVersionAccessors(providers, config);
        private final UiVersionAccessors vaccForUiVersionAccessors = new UiVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: browser (1.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBrowser() { return getVersion("browser"); }

            /**
             * Returns the version associated to this alias: coil (2.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoil() { return getVersion("coil"); }

            /**
             * Returns the version associated to this alias: ktx (1.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtx() { return getVersion("ktx"); }

            /**
             * Returns the version associated to this alias: mockito (5.3.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMockito() { return getVersion("mockito"); }

            /**
             * Returns the version associated to this alias: retrofit (2.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRetrofit() { return getVersion("retrofit"); }

            /**
             * Returns the version associated to this alias: turbine (0.12.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTurbine() { return getVersion("turbine"); }

        /**
         * Returns the group of versions at versions.activity
         */
        public ActivityVersionAccessors getActivity() { return vaccForActivityVersionAccessors; }

        /**
         * Returns the group of versions at versions.android
         */
        public AndroidVersionAccessors getAndroid() { return vaccForAndroidVersionAccessors; }

        /**
         * Returns the group of versions at versions.arch
         */
        public ArchVersionAccessors getArch() { return vaccForArchVersionAccessors; }

        /**
         * Returns the group of versions at versions.compose
         */
        public ComposeVersionAccessors getCompose() { return vaccForComposeVersionAccessors; }

        /**
         * Returns the group of versions at versions.converter
         */
        public ConverterVersionAccessors getConverter() { return vaccForConverterVersionAccessors; }

        /**
         * Returns the group of versions at versions.coroutines
         */
        public CoroutinesVersionAccessors getCoroutines() { return vaccForCoroutinesVersionAccessors; }

        /**
         * Returns the group of versions at versions.espresso
         */
        public EspressoVersionAccessors getEspresso() { return vaccForEspressoVersionAccessors; }

        /**
         * Returns the group of versions at versions.hilt
         */
        public HiltVersionAccessors getHilt() { return vaccForHiltVersionAccessors; }

        /**
         * Returns the group of versions at versions.junit
         */
        public JunitVersionAccessors getJunit() { return vaccForJunitVersionAccessors; }

        /**
         * Returns the group of versions at versions.kotlin
         */
        public KotlinVersionAccessors getKotlin() { return vaccForKotlinVersionAccessors; }

        /**
         * Returns the group of versions at versions.lifecycle
         */
        public LifecycleVersionAccessors getLifecycle() { return vaccForLifecycleVersionAccessors; }

        /**
         * Returns the group of versions at versions.material3
         */
        public Material3VersionAccessors getMaterial3() { return vaccForMaterial3VersionAccessors; }

        /**
         * Returns the group of versions at versions.navigation
         */
        public NavigationVersionAccessors getNavigation() { return vaccForNavigationVersionAccessors; }

        /**
         * Returns the group of versions at versions.paging
         */
        public PagingVersionAccessors getPaging() { return vaccForPagingVersionAccessors; }

        /**
         * Returns the group of versions at versions.room
         */
        public RoomVersionAccessors getRoom() { return vaccForRoomVersionAccessors; }

        /**
         * Returns the group of versions at versions.ui
         */
        public UiVersionAccessors getUi() { return vaccForUiVersionAccessors; }

    }

    public static class ActivityVersionAccessors extends VersionFactory  {

        public ActivityVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: activity.compose (1.7.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("activity.compose"); }

    }

    public static class AndroidVersionAccessors extends VersionFactory  {

        private final AndroidGradleVersionAccessors vaccForAndroidGradleVersionAccessors = new AndroidGradleVersionAccessors(providers, config);
        public AndroidVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.android.Gradle
         */
        public AndroidGradleVersionAccessors getGradle() { return vaccForAndroidGradleVersionAccessors; }

    }

    public static class AndroidGradleVersionAccessors extends VersionFactory  {

        public AndroidGradleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: android.Gradle.Plugin (7.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPlugin() { return getVersion("android.Gradle.Plugin"); }

    }

    public static class ArchVersionAccessors extends VersionFactory  {

        private final ArchCoreVersionAccessors vaccForArchCoreVersionAccessors = new ArchCoreVersionAccessors(providers, config);
        public ArchVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.arch.core
         */
        public ArchCoreVersionAccessors getCore() { return vaccForArchCoreVersionAccessors; }

    }

    public static class ArchCoreVersionAccessors extends VersionFactory  {

        public ArchCoreVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: arch.core.testing (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTesting() { return getVersion("arch.core.testing"); }

    }

    public static class ComposeVersionAccessors extends VersionFactory  {

        private final ComposeTestVersionAccessors vaccForComposeTestVersionAccessors = new ComposeTestVersionAccessors(providers, config);
        public ComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: compose.bom (2024.06.00)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBom() { return getVersion("compose.bom"); }

        /**
         * Returns the group of versions at versions.compose.test
         */
        public ComposeTestVersionAccessors getTest() { return vaccForComposeTestVersionAccessors; }

    }

    public static class ComposeTestVersionAccessors extends VersionFactory  {

        public ComposeTestVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: compose.test.junit4 (1.6.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit4() { return getVersion("compose.test.junit4"); }

    }

    public static class ConverterVersionAccessors extends VersionFactory  {

        public ConverterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: converter.gson (2.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGson() { return getVersion("converter.gson"); }

    }

    public static class CoroutinesVersionAccessors extends VersionFactory  {

        public CoroutinesVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: coroutines.test (1.7.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTest() { return getVersion("coroutines.test"); }

    }

    public static class EspressoVersionAccessors extends VersionFactory  {

        public EspressoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: espresso.core (3.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("espresso.core"); }

    }

    public static class HiltVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        private final HiltNavigationVersionAccessors vaccForHiltNavigationVersionAccessors = new HiltNavigationVersionAccessors(providers, config);
        public HiltVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the version associated to this alias: hilt (2.44)
         * If the version is a rich version and that its not expressible as a
         * single version string, then an empty string is returned.
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("hilt"); }

            /**
             * Returns the version associated to this alias: hilt.android (2.44)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("hilt.android"); }

        /**
         * Returns the group of versions at versions.hilt.navigation
         */
        public HiltNavigationVersionAccessors getNavigation() { return vaccForHiltNavigationVersionAccessors; }

    }

    public static class HiltNavigationVersionAccessors extends VersionFactory  {

        public HiltNavigationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: hilt.navigation.compose (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("hilt.navigation.compose"); }

    }

    public static class JunitVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        public JunitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the version associated to this alias: junit (4.13.2)
         * If the version is a rich version and that its not expressible as a
         * single version string, then an empty string is returned.
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: junit.ext (1.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getExt() { return getVersion("junit.ext"); }

    }

    public static class KotlinVersionAccessors extends VersionFactory  {

        public KotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: kotlin.android (1.8.10)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("kotlin.android"); }

    }

    public static class LifecycleVersionAccessors extends VersionFactory  {

        private final LifecycleRuntimeVersionAccessors vaccForLifecycleRuntimeVersionAccessors = new LifecycleRuntimeVersionAccessors(providers, config);
        private final LifecycleViewmodelVersionAccessors vaccForLifecycleViewmodelVersionAccessors = new LifecycleViewmodelVersionAccessors(providers, config);
        public LifecycleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.lifecycle.runtime
         */
        public LifecycleRuntimeVersionAccessors getRuntime() { return vaccForLifecycleRuntimeVersionAccessors; }

        /**
         * Returns the group of versions at versions.lifecycle.viewmodel
         */
        public LifecycleViewmodelVersionAccessors getViewmodel() { return vaccForLifecycleViewmodelVersionAccessors; }

    }

    public static class LifecycleRuntimeVersionAccessors extends VersionFactory  {

        public LifecycleRuntimeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: lifecycle.runtime.compose (2.6.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("lifecycle.runtime.compose"); }

    }

    public static class LifecycleViewmodelVersionAccessors extends VersionFactory  {

        public LifecycleViewmodelVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: lifecycle.viewmodel.compose (2.6.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("lifecycle.viewmodel.compose"); }

    }

    public static class Material3VersionAccessors extends VersionFactory  {

        public Material3VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: material3.android (1.3.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroid() { return getVersion("material3.android"); }

    }

    public static class NavigationVersionAccessors extends VersionFactory  {

        public NavigationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: navigation.compose (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("navigation.compose"); }

            /**
             * Returns the version associated to this alias: navigation.testing (2.7.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTesting() { return getVersion("navigation.testing"); }

    }

    public static class PagingVersionAccessors extends VersionFactory  {

        public PagingVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: paging.compose (3.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompose() { return getVersion("paging.compose"); }

            /**
             * Returns the version associated to this alias: paging.runtime (3.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRuntime() { return getVersion("paging.runtime"); }

    }

    public static class RoomVersionAccessors extends VersionFactory  {

        public RoomVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: room.compiler (2.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompiler() { return getVersion("room.compiler"); }

            /**
             * Returns the version associated to this alias: room.ktx (2.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtx() { return getVersion("room.ktx"); }

            /**
             * Returns the version associated to this alias: room.runtime (2.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRuntime() { return getVersion("room.runtime"); }

    }

    public static class UiVersionAccessors extends VersionFactory  {

        private final UiTestVersionAccessors vaccForUiTestVersionAccessors = new UiTestVersionAccessors(providers, config);
        public UiVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: ui.tooling (1.6.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTooling() { return getVersion("ui.tooling"); }

        /**
         * Returns the group of versions at versions.ui.test
         */
        public UiTestVersionAccessors getTest() { return vaccForUiTestVersionAccessors; }

    }

    public static class UiTestVersionAccessors extends VersionFactory  {

        public UiTestVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: ui.test.junit4 (1.6.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit4() { return getVersion("ui.test.junit4"); }

            /**
             * Returns the version associated to this alias: ui.test.manifest (1.6.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getManifest() { return getVersion("ui.test.manifest"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for androidapplication to the plugin id 'com.android.application'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroidapplication() { return createPlugin("androidapplication"); }

            /**
             * Creates a plugin provider for androidlibrary to the plugin id 'com.android.library'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroidlibrary() { return createPlugin("androidlibrary"); }

            /**
             * Creates a plugin provider for hiltandroid to the plugin id 'com.google.dagger.hilt.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getHiltandroid() { return createPlugin("hiltandroid"); }

            /**
             * Creates a plugin provider for kotlinandroid to the plugin id 'org.jetbrains.kotlin.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlinandroid() { return createPlugin("kotlinandroid"); }

    }

}
