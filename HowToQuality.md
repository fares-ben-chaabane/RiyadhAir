# How To Quality - RiyadhAir Project

## Overview

This document describes the quality tools and practices used in the RiyadhAir project to ensure code quality, consistency, and maintainability. The project uses several tools including Detekt, Konsist, JaCoCo, and Dokka for comprehensive quality assurance.

## Quality Tools Overview

### 1. Detekt - Static Code Analysis
**Purpose**: Static code analysis for Kotlin to detect code smells, bugs, and enforce coding standards.

**Version**: 1.23.5

### 2. Konsist - Architectural Consistency
**Purpose**: Kotlin static analysis tool for architectural and code style rules.

**Version**: 0.17.3

### 3. JaCoCo - Code Coverage
**Purpose**: Code coverage analysis to measure test effectiveness.

**Integration**: Built into Android Gradle Plugin

### 4. Dokka - API Documentation
**Purpose**: Kotlin API documentation generator.

**Version**: 2.0.0

## Detekt - Code Quality Analysis

### Configuration

**Location**: `config/detekt/detekt.yml`

**Key Configuration**:
```yaml
build:
  maxIssues: 0
  excludeCorrectable: false
  weights:
    complexity: 2
    LongParameterList: 1
    style: 1
    comments: 1

processors:
  active: true

console-reports:
  active: true
  exclude:
    - 'ProjectStatisticsReport'
    - 'ComplexityReport'
    - 'NotificationReport'
    - 'FileBasedFindingsReport'

output-reports:
  active: true
  exclude:
    - 'txt'
    - 'xml'
    - 'html'
```

### Running Detekt

#### Command Line
```bash
# Run Detekt on all modules
./gradlew detekt

# Run Detekt on specific module
./gradlew :app:detekt

# Run with HTML report
./gradlew detekt --report html

# Run with baseline (ignore existing issues)
./gradlew detekt --baseline config/detekt/baseline.xml
```

#### IDE Integration
- **Android Studio**: Install Detekt plugin
- **IntelliJ IDEA**: Install Detekt plugin
- **VS Code**: Install Detekt extension

### Detekt Rules and Customization

#### Built-in Rules
- **Complexity**: Function complexity, cyclomatic complexity
- **Style**: Naming conventions, formatting
- **Performance**: Inefficient operations
- **Security**: Potential security issues
- **Coroutines**: Coroutine best practices

#### Custom Rules
```kotlin
// Example custom rule
class CustomRule : Rule() {
    override val issue = Issue(
        id = "CustomRule",
        severity = Severity.Warning,
        description = "Custom rule description",
        debt = Debt.FIVE_MINS
    )

    override fun visitFunction(function: KtFunction) {
        // Rule implementation
    }
}
```

#### Rule Configuration
```yaml
complexity:
  LongParameterList:
    active: true
    threshold: 6
    functionThreshold: 10
    constructorThreshold: 7

style:
  MagicNumber:
    active: true
    ignoreNumbers: ['0', '1', '-1']
    ignoreHashCodeFunction: true
    ignorePropertyDeclaration: true
    ignoreLocalVariableDeclaration: true
    ignoreFunctionDeclaration: true
    ignoreAnnotatedFunction: []
```

### Detekt Reports

**Location**: `[module]/build/reports/detekt/`

**Report Types**:
- **HTML**: `detekt-report.html` - Visual report with navigation
- **XML**: `detekt-report.xml` - Machine-readable format
- **TXT**: `detekt-report.txt` - Plain text summary

**Report Content**:
- Issue count by severity
- File-level issue breakdown
- Rule violation details
- Code snippets with issues highlighted

### Fixing Detekt Issues

#### Common Issues and Solutions

1. **LongParameterList**
   ```kotlin
   // Before
   fun processUser(id: String, name: String, email: String, phone: String, address: String, preferences: List<String>)
   
   // After
   data class UserData(
       val id: String,
       val name: String,
       val email: String,
       val phone: String,
       val address: String,
       val preferences: List<String>
   )
   
   fun processUser(userData: UserData)
   ```

2. **ComplexMethod**
   ```kotlin
   // Before
   fun complexMethod() {
       // 50+ lines of complex logic
   }
   
   // After
   fun complexMethod() {
       val data = prepareData()
       val result = processData(data)
       saveResult(result)
   }
   
   private fun prepareData() = // ...
   private fun processData(data: Data) = // ...
   private fun saveResult(result: Result) = // ...
   ```

3. **MagicNumber**
   ```kotlin
   // Before
   if (count > 10) { /* ... */ }
   
   // After
   companion object {
       private const val MAX_COUNT = 10
   }
   
   if (count > MAX_COUNT) { /* ... */ }
   ```

## Konsist - Architectural and team rules Consistency

### Purpose
Konsist ensures architectural consistency and team rules respecting by enforcing package structure, naming conventions, and architectural rules.

### Configuration

**Location**: `app/src/test/java/fr/benchaabane/riyadhair/konsist/`

**Key Test Files**:
- `BasicKonsistTest.kt` - Basic architectural rules
- `CleanArchitectureTest.kt` - Clean architecture enforcement
- `ComposePreviewTest.kt` - Compose preview validation

### Running Konsist

#### Command Line
```bash
# Run Konsist tests
./gradlew :app:test --tests "*Konsist*"

# Run specific Konsist test
./gradlew :app:test --tests "*CleanArchitectureTest*"
```

#### IDE Integration
- Run as regular unit tests
- Use test runner in Android Studio/IntelliJ

### Konsist Rules and Examples

#### 1. Package Structure Validation
```kotlin
@Test
fun `domain layer should not depend on data layer`() {
    Konsist
        .scopeFromProject()
        .classes()
        .withPackage("..domain..")
        .shouldNotDependOn("..data..")
}
```

#### 2. Naming Convention Validation
```kotlin
@Test
fun `use case classes should end with UseCase`() {
    Konsist
        .scopeFromProject()
        .classes()
        .withPackage("..domain..usecases..")
        .shouldHaveNameEndingWith("UseCase")
}
```

#### 3. Architecture Layer Validation
```kotlin
@Test
fun `presentation layer should not depend on data layer`() {
    Konsist
        .scopeFromProject()
        .classes()
        .withPackage("..presentation..")
        .shouldNotDependOn("..data..")
}
```

#### 4. Repository Interface Validation
```kotlin
@Test
fun `repository interfaces should be in domain layer`() {
    Konsist
        .scopeFromProject()
        .interfaces()
        .withNameEndingWith("Repository")
        .shouldResideInPackage("..domain..repositories..")
}
```

### Creating Custom Konsist Rules

#### Custom Rule Example
```kotlin
@Test
fun `view models should follow naming convention`() {
    Konsist
        .scopeFromProject()
        .classes()
        .withNameEndingWith("ViewModel")
        .shouldResideInPackage("..presentation..")
        .shouldHaveNameStartingWith("")
        .shouldHaveNameEndingWith("ViewModel")
}
```

#### Complex Rule Example
```kotlin
@Test
fun `use cases should have single public method`() {
    Konsist
        .scopeFromProject()
        .classes()
        .withPackage("..domain..usecases..")
        .withNameEndingWith("UseCase")
        .should { useCase ->
            val publicMethods = useCase.declaredMethods.filter { it.isPublic }
            publicMethods.size == 1 && publicMethods.first().name == "invoke"
        }
}
```

## JaCoCo - Code Coverage

### Purpose
JaCoCo provides code coverage analysis to measure how much of the code is executed during testing.

### Configuration

**Location**: Module `build.gradle.kts` files

**Configuration Example**:
```kotlin
android {
    buildTypes {
        debug {
            isTestCoverageEnabled = true
        }
    }
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")
    
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
    
    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*"
    )
    
    val debugTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    
    val mainSrc = "${project.projectDir}/src/main/java"
    
    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))
    executionData.setFrom(fileTree(project.buildDir) {
        include("/outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
    })
}
```

### Running Coverage Analysis

#### Command Line
```bash
# Generate coverage report for specific module
./gradlew :app:jacocoTestReport
./gradlew :domain:jacocoTestReport
./gradlew :data:jacocoTestReport
./gradlew :presentation:jacocoTestReport

# Generate coverage for all modules
./gradlew jacocoRootReport
```

#### Root-level Coverage Task
```bash
# Run from root build.gradle.kts
./gradlew jacocoRootReport
```

### Coverage Reports

**Location**: `[module]/build/reports/jacoco/test/html/index.html`

**Report Content**:
- **Line Coverage**: Percentage of lines executed
- **Branch Coverage**: Percentage of branches executed
- **Method Coverage**: Percentage of methods executed
- **Class Coverage**: Percentage of classes executed

**Coverage Metrics**:
- **Green**: Fully covered (100%)
- **Yellow**: Partially covered (1-99%)
- **Red**: Not covered (0%)

### Coverage Goals and Thresholds

#### Recommended Coverage Targets
- **Domain Layer**: 90%+ (business logic critical)
- **Data Layer**: 80%+ (data operations)
- **Presentation Layer**: 70%+ (UI logic)
- **Overall Project**: 80%+

#### Setting Coverage Thresholds
```kotlin
tasks.register<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    dependsOn("jacocoTestReport")
    
    violationRules {
        rule {
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
        }
    }
}
```

### Improving Code Coverage

#### 1. Identify Uncovered Code
- Review coverage reports
- Focus on red and yellow areas
- Prioritize business logic

#### 2. Add Missing Tests
```kotlin
// Example: Add test for uncovered branch
@Test
fun `use case should handle error case`() = runTest {
    // Given
    coEvery { repository.getData() } throws Exception("Network error")
    
    // When
    val result = useCase.execute()
    
    // Then
    result.isFailure shouldBe true
    result.exceptionOrNull()?.message shouldBe "Network error"
}
```

#### 3. Test Edge Cases
```kotlin
@Test
fun `repository should handle empty list`() = runTest {
    // Given
    coEvery { apiService.getData() } returns emptyList()
    
    // When
    val result = repository.getData()
    
    // Then
    result shouldBe emptyList()
}
```

## Dokka - API Documentation

### Purpose
Dokka generates comprehensive API documentation for Kotlin code, including KDoc comments and source code references.

### Configuration

**Location**: Module `build.gradle.kts` files

**Configuration Example**:
```kotlin
plugins {
    alias(libs.plugins.dokka)
}

dokka {
    moduleName.set("RiyadhAir [ModuleName]")
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(false)
    }
    pluginsConfiguration.html {
        footerMessage.set("(c) Riyadh Air")
    }
}
```

### Running Dokka

#### Command Line
```bash
# Generate documentation for specific module
./gradlew :app:dokkaGenerateModuleHtml
./gradlew :domain:dokkaGenerateModuleHtml
./gradlew :data:dokkaGenerateModuleHtml
./gradlew :presentation:dokkaGenerateModuleHtml

# Generate multi-module documentation
./gradlew dokkaHtmlMultiModule
```

#### Documentation Output
**Location**: `[module]/build/dokka/html/`

**Content**:
- API reference documentation
- KDoc comments
- Source code links
- Package structure
- Class hierarchy

### KDoc Best Practices

#### 1. Class Documentation
```kotlin
/**
 * Represents a user account in the RiyadhAir system.
 *
 * This data class contains comprehensive user information including
 * personal details, loyalty program data, and user preferences.
 *
 * @property id Unique identifier for the user account
 * @property firstName User's first name
 * @property lastName User's last name
 * @property email User's email address
 *
 * @see AccountRepository
 * @see GetAccountUseCase
 */
data class Account(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
```

#### 2. Function Documentation
```kotlin
/**
 * Retrieves the user account from the repository.
 *
 * This function fetches the account data from the local database
 * or remote API, depending on availability and cache settings.
 *
 * @param userId The unique identifier of the user
 * @return The user account if found, null otherwise
 * @throws NetworkException if network request fails
 *
 * @sample
 * ```kotlin
 * val account = repository.getAccount("user123")
 * account?.let { user ->
 *     println("User: ${user.firstName} ${user.lastName}")
 * }
 * ```
 */
suspend fun getAccount(userId: String): Account?
```

#### 3. Property Documentation
```kotlin
/**
 * The user's current loyalty level in the RiyadhAir program.
 *
 * This property determines the user's benefits, discounts, and
 * service level within the loyalty program.
 */
val loyaltyLevel: LoyaltyLevel
```

## Quality Workflow

### 1. Pre-commit Quality Checks

#### Automated Checks
```bash
# Run all quality tools
./gradlew detekt
./gradlew test
./gradlew :app:jacocoTestReport
```

#### Manual Checks
- Review code for architectural violations
- Ensure proper KDoc documentation
- Check test coverage for new code

### 2. Continuous Integration

#### CI Pipeline Steps
1. **Code Analysis**: Run Detekt
2. **Testing**: Execute unit tests
3. **Coverage**: Generate JaCoCo reports
4. **Architecture**: Run Konsist tests
5. **Documentation**: Generate Dokka docs

#### Quality Gates
- Detekt: 0 critical issues
- Tests: All tests pass
- Coverage: Minimum 80% coverage
- Architecture: All Konsist tests pass

### 3. Quality Metrics Dashboard

#### Key Metrics
- **Code Quality**: Detekt issue count by severity
- **Test Coverage**: JaCoCo coverage by module
- **Architecture**: Konsist test results
- **Documentation**: KDoc coverage

#### Tracking Tools
- Detekt HTML reports
- JaCoCo coverage reports
- Konsist test results
- Dokka documentation

## Best Practices

### 1. Code Quality

#### Detekt Integration
- Run Detekt before committing code
- Address all critical and major issues
- Use baseline for legacy code
- Customize rules for project needs

#### Code Review
- Review Detekt reports during code review
- Ensure consistent code style
- Check for architectural violations
- Verify test coverage

### 2. Testing Strategy

#### Coverage Goals
- Set realistic coverage targets
- Focus on business logic coverage
- Test edge cases and error scenarios
- Maintain coverage over time

#### Test Quality
- Write meaningful tests
- Use descriptive test names
- Follow Given-When-Then pattern
- Mock external dependencies

### 3. Architecture Consistency

#### Konsist Rules
- Define clear architectural boundaries
- Enforce naming conventions
- Validate package structure
- Prevent dependency violations

#### Regular Validation
- Run Konsist tests regularly
- Update rules as architecture evolves
- Document architectural decisions
- Train team on architecture principles

### 4. Documentation

#### KDoc Standards
- Document all public APIs
- Use clear and concise descriptions
- Include usage examples
- Maintain documentation quality

#### Documentation Review
- Review KDoc during code review
- Ensure examples are up-to-date
- Validate generated documentation
- Keep documentation current

## Troubleshooting

### Common Issues

#### 1. Detekt Issues

**Problem**: High number of false positives
**Solution**: Customize rule configuration, use baseline for legacy code

**Problem**: Performance issues with large codebase
**Solution**: Use incremental analysis, exclude generated code

#### 2. Konsist Issues

**Problem**: Tests failing due to architectural changes
**Solution**: Update rules to match new architecture, review architectural decisions

**Problem**: False positive dependency violations
**Solution**: Review module dependencies, update Konsist rules

#### 3. JaCoCo Issues

**Problem**: Coverage reports not generated
**Solution**: Check build configuration, ensure tests are running

**Problem**: Coverage targets not met
**Solution**: Add missing tests, focus on uncovered business logic

#### 4. Dokka Issues

**Problem**: Documentation not generated
**Solution**: Check plugin configuration, verify KDoc syntax

**Problem**: Missing source links
**Solution**: Configure source sets, check repository URLs

### Debug Tips

#### 1. Detekt Debugging
```bash
# Run with verbose output
./gradlew detekt --info

# Generate detailed report
./gradlew detekt --report html,xml,txt
```

#### 2. Konsist Debugging
```bash
# Run specific test with debug output
./gradlew :app:test --tests "*Konsist*" --info
```

#### 3. JaCoCo Debugging
```bash
# Check execution data
ls -la [module]/build/outputs/unit_test_code_coverage/

# Verify test execution
./gradlew :[module]:testDebugUnitTest
```

#### 4. Dokka Debugging
```bash
# Check plugin configuration
./gradlew :[module]:dokkaGenerateModuleHtml --info

# Verify source sets
./gradlew :[module]:dokkaGenerateModuleHtml --debug
```

## Resources

### Documentation
- [Detekt Documentation](https://detekt.dev/)
- [Konsist Documentation](https://docs.konsist.lemonappdev.com/)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Dokka Documentation](https://kotlin.github.io/dokka/)

### Project References
- See `HowToUnitTest.md` for testing guidelines
- See `HowToArchitecture.md` for project structure
- Check existing quality configurations
- Review quality tool outputs

### Community Resources
- [Detekt GitHub](https://github.com/detekt/detekt)
- [Konsist GitHub](https://github.com/lemonappdev/konsist)
- [JaCoCo GitHub](https://github.com/jacoco/jacoco)
- [Dokka GitHub](https://github.com/Kotlin/dokka)

