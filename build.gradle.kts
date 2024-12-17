plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.netflix.dgs.codegen") version "6.3.0"
}
val netflixDgsVersion by extra("9.1.3")

group = "com.example.graphql_dgs"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")
	implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:6.3.0")
	implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")
//	implementation ("com.netflix.graphql.dgs:graphql-dgs-subscriptions-websockets-autoconfigure:latest.release")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.ocpsoft.prettytime:prettytime:5.0.8.Final")
	implementation("org.bouncycastle:bcprov-jdk15on:1.68")

	implementation("net.datafaker:datafaker:2.4.1")

	implementation("com.graphql-java:graphql-java-extended-scalars:22.0")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.projectlombok:lombok")
	implementation("org.apache.commons:commons-lang3:3.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	runtimeOnly("org.postgresql:postgresql")




}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}
dependencyManagement {
	imports {
		mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:$netflixDgsVersion")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask>{
	generateClient = true
	typeMapping = mutableMapOf(
		"Url" to "java.net.URL",
		"PositiveInt" to "kotlin.Int",
		"DateTime" to "java.time.OffsetDateTime",
		"NonNegativeInt" to "kotlin.Int",
	)
}
