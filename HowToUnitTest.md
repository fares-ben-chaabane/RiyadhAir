# How To Unit Test - RiyadhAir Project

## Overview

This document describes the unit testing strategy, structure, and practices used in the RiyadhAir project. The project follows a comprehensive testing approach with JUnit, Kluent, Mockk, and other testing tools.

## Testing Architecture (Guerkin Approach)

### Test Structure
```
src/
├── main/
│   └── java/
│       └── fr/benchaabane/riyadhair/
│           └── [module]/
│               └── [feature]/
└── test/
    └── java/
        └── fr/benchaabane/riyadhair/
            └── [module]/
                └── [feature]/
                    └── [ClassName]Test.kt
```

### Test Naming Convention
- **Test Files**: `[ClassName]Test.kt`
- **Test Functions**: `[methodName]_[scenario]_[expectedResult]()`
- **Test Classes**: `[ClassName]Test`

## Testing Dependencies

### Core Testing Libraries
```kotlin
// From libs.versions.toml
testImplementation(libs.bundles.test)
// Which includes:
// - junit: "4.13.2"
// - kluent: "1.72" (assertions)
// - mockk: "1.13.8" (mocking)
// - coroutines-test: "1.10.2" (coroutine testing)
```

### Additional Testing Libraries
```kotlin
// For Flow testing
testImplementation(libs.turbine)

// For architectural consistency
testImplementation(libs.konsist)
```

## Test Organization by Layer

### 1. Domain Layer Tests (`:domain`)
**Location**: `domain/src/test/java/`

**What to Test**:
- Use Cases (Business Logic)
- Domain Models
- Repository Interfaces

**Example Structure**:
```
domain/src/test/java/fr/benchaabane/riyadhair/domain/
├── account/
│   └── usecases/
│       ├── GetAccountUseCaseTest.kt
│       └── RefreshAccountUseCaseTest.kt
├── flights/
│   └── usecases/
│       ├── GetFlightsPaginatedUseCaseTest.kt
│       └── SearchFlightsUseCaseTest.kt
└── offers/
    └── usecases/
        └── GetBestOffersUseCaseTest.kt
```

### 2. Data Layer Tests (`:data`)
**Location**: `data/src/test/java/`

**What to Test**:
- Repository Implementations
- Mappers
- Data Sources (API, Database)

**Example Structure**:
```
data/src/test/java/fr/benchaabane/riyadhair/data/
├── account/
│   ├── mappers/
│   │   └── AccountMappersTest.kt
│   └── repositories/
│       └── AccountRepositoryImplTest.kt
├── flights/
│   ├── mappers/
│   │   └── FlightMappersTest.kt
│   └── repositories/
│       └── FlightRepositoryImplTest.kt
└── offers/
    ├── mappers/
    │   └── OfferMappersTest.kt
    └── repositories/
        └── OffersRepositoryImplTest.kt
```

### 3. Presentation Layer Tests (`:presentation`)
**Location**: `presentation/src/test/java/`

**What to Test**:
- ViewModels
- UI State Management
- Navigation Logic

**Example Structure**:
```
presentation/src/test/java/fr/benchaabane/riyadhair/presentation/
├── account/
│   ├── AccountViewModelTest.kt
│   └── components/
│       └── MemberCardTest.kt
├── home/
│   └── HomeViewModelTest.kt
├── search/
│   ├── SearchViewModelTest.kt
│   └── SearchResultsViewModelTest.kt
└── offers/
    └── OffersViewModelTest.kt
```

## Test Writing Guidelines

### 1. Test Structure (Given-When-Then)
```kotlin
@Test
fun `getAccount_whenUserExists_returnsAccount`() = runTest {
    // Given
    val userId = "user123"
    val expectedAccount = Account(id = userId, firstName = "John")
    coEvery { accountRepository.getAccount(userId) } returns expectedAccount
    
    // When
    val result = getAccountUseCase(userId)
    
    // Then
    result shouldBe expectedAccount
    coVerify { accountRepository.getAccount(userId) }
}
```

### 2. Test Annotations
```kotlin
@get:Rule
val instantExecutorRule = InstantTaskExecutorRule()

@Before
fun setup() {
    // Initialize test components
}

@Test
fun `test description`() {
    // Test implementation
}
```

### 3. Mocking with Mockk
```kotlin
// Mock creation
val mockRepository = mockk<AccountRepository>()

// Behavior setup
every { mockRepository.getAccount(any()) } returns mockAccount
coEvery { mockRepository.saveAccount(any()) } just Runs

// Verification
verify { mockRepository.getAccount(any()) }
coVerify { mockRepository.saveAccount(any()) }
```

### 4. Coroutine Testing
```kotlin
@Test
fun `test coroutine function`() = runTest {
    // Given
    val expectedResult = "success"
    
    // When
    val result = suspendFunction()
    
    // Then
    result shouldBe expectedResult
}
```

### 5. Flow Testing with Turbine
```kotlin
@Test
fun `test flow emissions`() = runTest {
    // Given
    val flow = MutableStateFlow("initial")
    
    // When & Then
    flow.test {
        expectItem() shouldBe "initial"
        
        flow.value = "updated"
        expectItem() shouldBe "updated"
        
        awaitComplete()
    }
}
```

## Running Tests

### Command Line Commands

#### Run All Tests
```bash
./gradlew test
```

#### Run Tests for Specific Module
```bash
./gradlew :app:test
./gradlew :domain:test
./gradlew :data:test
./gradlew :presentation:test
./gradlew :core:test
./gradlew :design_system:test
```

#### Run Tests with Coverage
```bash
./gradlew :app:jacocoTestReport
./gradlew :domain:jacocoTestReport
./gradlew :data:jacocoTestReport
./gradlew :presentation:jacocoTestReport
```

#### Run Tests with Debug Output
```bash
./gradlew test --info
./gradlew test --debug
```

#### Run Specific Test Class
```bash
./gradlew :app:test --tests "*AccountViewModelTest*"
```

#### Run Specific Test Method
```bash
./gradlew :app:test --tests "*AccountViewModelTest.getAccount_whenUserExists_returnsAccount*"
```

### IDE Integration

#### Android Studio / IntelliJ
1. Right-click on test file → "Run Tests"
2. Right-click on test method → "Run Test"
3. Use Ctrl+Shift+F10 (Windows/Linux) or Cmd+Shift+R (Mac) to run tests
4. Use Ctrl+Shift+F9 (Windows/Linux) or Cmd+Shift+F9 (Mac) to debug tests

#### Test Results
- **Test Results Window**: View → Tool Windows → Test
- **Coverage Window**: View → Tool Windows → Coverage
- **Run Window**: View → Tool Windows → Run

## Test Reports

### JaCoCo Coverage Reports
**Location**: `[module]/build/reports/jacoco/test/html/index.html`

**How to View**:
1. Run coverage: `./gradlew :[module]:jacocoTestReport`
2. Open HTML report in browser
3. Navigate to `[module]/build/reports/jacoco/test/html/index.html`

**Coverage Metrics**:
- **Line Coverage**: Percentage of lines executed
- **Branch Coverage**: Percentage of branches executed
- **Method Coverage**: Percentage of methods executed
- **Class Coverage**: Percentage of classes executed

### Test Execution Reports
**Location**: `[module]/build/reports/tests/test/index.html`

**How to View**:
1. Run tests: `./gradlew :[module]:test`
2. Open HTML report in browser
3. Navigate to `[module]/build/reports/tests/test/index.html`

## Creating New Tests

### 1. Test File Structure
```kotlin
package fr.benchaabane.riyadhair.[module].[feature]

import fr.benchaabane.riyadhair.domain.[feature].models.[Model]
import fr.benchaabane.riyadhair.domain.[feature].repositories.[Repository]
import fr.benchaabane.riyadhair.domain.[feature].usecases.[UseCase]
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test

class [ClassName]Test {
    
    @Before
    fun setup() {
        // Initialize mocks and test data
    }
    
    @Test
    fun `test description`() = runTest {
        // Given-When-Then implementation
    }
}
```

### 2. Test Data Creation
```kotlin
// Create test data objects
private val testAccount = Account(
    id = "test123",
    firstName = "Test",
    lastName = "User",
    email = "test@example.com"
)

// Create mock repositories
private val mockRepository = mockk<AccountRepository>()

// Create use case with mocked dependencies
private val useCase = GetAccountUseCase(mockRepository)
```

### 3. Test Categories

#### Unit Tests
- Test individual components in isolation
- Mock all external dependencies
- Focus on business logic

#### Integration Tests
- Test component interactions
- Use real implementations where possible
- Test data flow between layers

#### Contract Tests
- Test repository interfaces
- Ensure consistent behavior across implementations
- Test data mapping and transformations

## Best Practices

### 1. Test Naming
- Use descriptive names that explain the scenario
- Follow the pattern: `[method]_[scenario]_[expectedResult]`
- Use backticks for test names with spaces

### 2. Test Organization
- Group related tests in the same test class
- Use `@Nested` classes for complex test scenarios
- Keep tests focused and single-purpose

### 3. Mocking Strategy
- Mock at the boundary of the unit under test
- Use `coEvery` for suspend functions
- Verify important interactions with `verify` or `coVerify`

### 4. Test Data
- Create reusable test data objects
- Use factory methods for complex objects
- Keep test data realistic but minimal

### 5. Assertions
- Use Kluent assertions for readability
- Assert one thing per test
- Use descriptive assertion messages

## Common Testing Patterns

### 1. Repository Testing
```kotlin
@Test
fun `getAccount_whenUserExists_returnsAccount`() = runTest {
    // Given
    val userId = "user123"
    val expectedAccount = testAccount
    coEvery { repository.getAccount(userId) } returns expectedAccount
    
    // When
    val result = repository.getAccount(userId)
    
    // Then
    result shouldBe expectedAccount
}
```

### 2. Use Case Testing
```kotlin
@Test
fun `execute_whenRepositoryReturnsData_returnsSuccess`() = runTest {
    // Given
    val input = "input"
    val expectedResult = Result.success("output")
    coEvery { repository.process(input) } returns expectedResult
    
    // When
    val result = useCase(input)
    
    // Then
    result shouldBe expectedResult
}
```

### 3. ViewModel Testing
```kotlin
@Test
fun `loadData_whenSuccessful_updatesState`() = runTest {
    // Given
    val data = listOf("item1", "item2")
    coEvery { repository.getData() } returns data
    
    // When
    viewModel.loadData()
    
    // Then
    viewModel.state.value shouldBe ViewState.Success(data)
}
```

## Troubleshooting

### Common Issues

#### 1. Test Hanging
- Ensure all coroutines complete
- Use `runTest` for suspend functions
- Check for infinite loops in mocks

#### 2. Mock Verification Failing
- Verify the correct number of calls
- Check mock setup and behavior
- Ensure mocks are properly initialized

#### 3. Test Data Issues
- Verify test data creation
- Check for null values
- Ensure proper object initialization

### Debug Tips
- Use `--info` or `--debug` flags for verbose output
- Add logging to test methods
- Use IDE debugger for complex test scenarios
- Check test reports for detailed failure information

## Resources

### Documentation
- [JUnit 4 User Guide](https://junit.org/junit4/)
- [Mockk Documentation](https://mockk.io/)
- [Kluent Assertions](https://github.com/MarkusAmshove/Kluent)
- [Kotlin Coroutines Testing](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/)

### Project References
- See `HowToArchitecture.md` for project structure
- See `HowToQuality.md` for quality tools integration
- Check existing test files for examples and patterns

