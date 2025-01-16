plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot 기본 의존성
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// 유틸리티 라이브러리
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.apache.commons:commons-csv:1.10.0")

	// Lombok 설정
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// MapStruct 설정
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")


	// 테스트 관련 의존성
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
	testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile> {
	options.annotationProcessorPath = configurations.annotationProcessor.get().asFileTree
}

tasks.withType<Test> {
	useJUnitPlatform()
}

