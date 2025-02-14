plugins {
  `java-library`
  alias(libs.plugins.versions)
}

dependencies {
  implementation(libs.bundles.hibernate)
  implementation(libs.bundles.log4j2)
  implementation(libs.caffeine)
  runtimeOnly(libs.h2)

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}

testing.suites {
  val test by getting(JvmTestSuite::class) {
    useJUnitJupiter()
  }
}

java.toolchain.languageVersion = JavaLanguageVersion.of(
  System.getenv("JAVA_VERSION")?.toIntOrNull() ?: 11)

tasks.withType<JavaCompile>().configureEach {
  javaCompiler = javaToolchains.compilerFor {
    languageVersion = java.toolchain.languageVersion
  }
}
