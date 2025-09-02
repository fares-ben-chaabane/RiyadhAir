# How To Architecture - RiyadhAir Project

## Overview

This document describes the architecture of the RiyadhAir project, which follows Clean Architecture principles with clear separation of concerns across multiple layers. The project is organized into modules that represent different architectural layers and features.

## Architecture Overview

### Clean Architecture Principles : DDD (Domain Driven Development)

The project follows Clean Architecture principles with the following key concepts:

1. **Dependency Rule**: Dependencies point inward. Domain layer has no dependencies on outer layers.
2. **Separation of Concerns**: Each layer has a specific responsibility.
3. **Testability**: Business logic is isolated and easily testable.
4. **Independence**: Business rules are independent of frameworks and external concerns.

### Module Structure

```
RiyadhAir/
├── app/                    # Application module (entry point)
├── core/                   # Core utilities and shared components
├── design_system/          # UI components and design system
├── domain/                 # Business logic and domain models
├── data/                   # Data access and external integrations
└── presentation/           # UI layer and user interactions
```

## Layer Responsibilities

### 1. Domain Layer (`:domain`)

**Purpose**: Contains business logic, domain models, and business rules.

**Responsibilities**:
- Business entities and value objects
- Repository interfaces
- Use cases (business logic)
- Domain services
- Business rules and validation

**Structure**:
```
domain/src/main/java/fr/benchaabane/riyadhair/domain/
├── account/
│   ├── models/
│   │   ├── Account.kt
│   │   └── LoyaltyLevel.kt
│   ├── repositories/
│   │   └── AccountRepository.kt
│   └── usecases/
│       ├── GetAccountUseCase.kt
│       └── RefreshAccountUseCase.kt
├── flights/
│   ├── models/
│   │   ├── Flight.kt
│   │   └── Airport.kt
│   ├── repositories/
│   │   └── FlightRepository.kt
│   └── usecases/
│       ├── GetFlightsPaginatedUseCase.kt
│       └── SearchFlightsUseCase.kt
├── offers/
│   ├── models/
│   │   ├── Offer.kt
│   │   └── Destination.kt
│   ├── repositories/
│   │   └── OffersRepository.kt
│   └── usecases/
│       └── GetBestOffersUseCase.kt
├── partners/
│   ├── models/
│   │   └── Partner.kt
│   ├── repositories/
│   │   └── PartnersRepository.kt
│   └── usecases/
│       └── RefreshPartnersUseCase.kt
└── reservations/
    ├── models/
    │   └── Reservation.kt
    ├── repositories/
    │   └── ReservationRepository.kt
    └── usecases/
        ├── CreateReservationUseCase.kt
        └── GetReservationsUseCase.kt
```

**Key Principles**:
- No dependencies on Android framework
- Pure Kotlin/Java code
- Business logic only
- Repository interfaces (not implementations)

### 2. Data Layer (`:data`)

**Purpose**: Implements data access, external API integration, and local storage.

**Responsibilities**:
- Repository implementations
- API services and DTOs
- Database entities and DAOs
- Data mappers
- Network and caching logic

**Structure**:
```
data/src/main/java/fr/benchaabane/riyadhair/data/
├── account/
│   ├── api/
│   │   ├── AccountDto.kt
│   │   └── AccountService.kt
│   ├── dao/
│   │   ├── AccountDao.kt
│   │   └── AccountEntity.kt
│   ├── mappers/
│   │   └── AccountMappers.kt
│   └── repositories/
│       └── AccountRepositoryImpl.kt
├── flights/
│   ├── api/
│   │   ├── FlightDto.kt
│   │   └── FlightService.kt
│   ├── mappers/
│   │   └── FlightMappers.kt
│   ├── paging/
│   │   ├── FlightPagingSource.kt
│   │   └── FlightRemoteMediator.kt
│   └── repositories/
│       └── FlightRepositoryImpl.kt
├── offers/
│   ├── api/
│   │   ├── OfferDto.kt
│   │   └── OffersService.kt
│   ├── dao/
│   │   ├── OfferDao.kt
│   │   └── OfferEntity.kt
│   ├── mappers/
│   │   └── OfferMappers.kt
│   └── repositories/
│       └── OffersRepositoryImpl.kt
├── partners/
│   ├── api/
│   │   ├── PartnerDto.kt
│   │   └── PartnerService.kt
│   ├── dao/
│   │   ├── PartnerDao.kt
│   │   └── PartnerEntity.kt
│   ├── mappers/
│   │   └── PartnerMappers.kt
│   └── repositories/
│       └── PartnersRepositoryImpl.kt
├── reservations/
│   ├── api/
│   │   ├── ReservationDto.kt
│   │   └── ReservationService.kt
│   ├── dao/
│   │   ├── ReservationDao.kt
│   │   └── ReservationEntity.kt
│   ├── mappers/
│   │   └── ReservationMappers.kt
│   └── repositories/
│       └── ReservationRepositoryImpl.kt
└── db/
    └── AppDatabase.kt
```

**Key Principles**:
- Implements domain repository interfaces
- Handles data transformation (DTO ↔ Domain ↔ Entity)
- Manages caching and offline support
- Implements network-bound resource pattern

### 3. Presentation Layer (`:presentation`)

**Purpose**: Handles user interface, user interactions, and presentation logic.

**Responsibilities**:
- Activities and Fragments
- ViewModels
- UI state management
- Navigation
- User input handling

**Structure**:
```
presentation/src/main/java/fr/benchaabane/riyadhair/presentation/
├── MainActivity.kt
├── account/
│   ├── AccountScreen.kt
│   ├── AccountViewModel.kt
│   └── components/
│       ├── MemberCard.kt
│       └── LoyaltyInfo.kt
├── home/
│   ├── HomeScreen.kt
│   ├── HomeViewModel.kt
│   └── components/
│       ├── WelcomeSection.kt
│       └── QuickActions.kt
├── search/
│   ├── SearchScreen.kt
│   ├── SearchViewModel.kt
│   ├── SearchResultsScreen.kt
│   ├── SearchResultsViewModel.kt
│   └── components/
│       ├── SearchBar.kt
│       ├── FlightCard.kt
│       └── FilterOptions.kt
├── offers/
│   ├── OffersScreen.kt
│   ├── OffersViewModel.kt
│   └── components/
│       ├── OfferCard.kt
│       └── DestinationCard.kt
├── partners/
│   ├── PartnersScreen.kt
│   ├── PartnersViewModel.kt
│   └── components/
│       └── PartnerCard.kt
├── reservations/
│   ├── ReservationsScreen.kt
│   ├── ReservationsViewModel.kt
│   └── components/
│       └── ReservationCard.kt
├── checkout/
│   ├── CheckoutScreen.kt
│   └── components/
│       ├── TravelerInfoForm.kt
│       └── PassportInfoForm.kt
└── navigation/
    ├── NavigationRoutes.kt
    └── NavigationGraph.kt
```

**Key Principles**:
- Uses Jetpack Compose for UI
- Follows MVVM pattern with ViewModels
- Manages UI state with StateFlow
- Handles user interactions and navigation

### 4. Core Module (`:core`)

**Purpose**: Provides shared utilities, extensions, and common functionality.

**Responsibilities**:
- Utility functions and extensions
- Common interfaces and base classes
- Network utilities
- MRZ scanning functionality
- Adaptive UI utilities

**Structure**:
```
core/src/main/java/fr/benchaabane/riyadhair/core/
├── extensions/
│   └── ResultExtentions.kt
├── mrz/
│   ├── MrzAnalyzer.kt
│   └── MrzScanner.kt
├── network/
│   └── NetworkBoundResource.kt
├── pagination/
│   └── PaginationState.kt
└── ui/
    └── RtlUtils.kt
```

**Key Principles**:
- No business logic
- Reusable across all modules
- Framework-agnostic utilities
- Common patterns and extensions

### 5. Design System Module (`:design_system`)

**Purpose**: Provides consistent UI components and design tokens.

**Responsibilities**:
- Reusable UI components
- Design tokens (colors, typography, spacing)
- Theme definitions
- Icon sets

**Structure**:
```
design_system/src/main/java/fr/benchaabane/riyadhair/designsystem/
├── components/
│   ├── buttons/
│   │   └── RiyadhAirButton.kt
│   ├── cards/
│   │   ├── RiyadhAirCard.kt
│   │   └── RiyadhAirElevatedCard.kt
│   ├── datepicker/
│   │   └── RiyadhAirDatePicker.kt
│   ├── images/
│   │   └── RiyadhAirAsyncImage.kt
│   ├── selection/
│   │   ├── RiyadhAirDropdown.kt
│   │   └── RiyadhAirRadioGroup.kt
│   └── textfields/
│       └── RiyadhAirTextField.kt
├── icons/
│   └── Icons.kt
└── theme/
    ├── Colors.kt
    ├── Shapes.kt
    ├── Spacing.kt
    ├── Theme.kt
    └── Typography.kt
```

**Key Principles**:
- Consistent design language
- Reusable components
- Theme-aware components
- Accessibility support

### 6. App Module (`:app`)

**Purpose**: Application entry point and dependency injection setup.

**Responsibilities**:
- Application class
- Dependency injection modules
- Main activity
- App-level configuration

**Structure**:
```
app/src/main/java/fr/benchaabane/riyadhair/
├── RiyadhAirApp.kt
├── di/
│   ├── AppModule.kt
│   ├── DatabaseModule.kt
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
└── network/
    └── MockInterceptor.kt
```

**Key Principles**:
- Minimal business logic
- Dependency injection setup
- App-level configuration
- Entry point coordination

## Adding New Features

### Step-by-Step Process

#### 1. Define Domain Models

Start in the `:domain` module:

```kotlin
// domain/src/main/java/fr/benchaabane/riyadhair/domain/feature/models/Feature.kt
data class Feature(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean
)
```

#### 2. Define Repository Interface

```kotlin
// domain/src/main/java/fr/benchaabane/riyadhair/domain/feature/repositories/FeatureRepository.kt
interface FeatureRepository {
    suspend fun getFeatures(): List<Feature>
    suspend fun getFeature(id: String): Feature?
    suspend fun createFeature(feature: Feature): Result<Feature>
    suspend fun updateFeature(feature: Feature): Result<Feature>
    suspend fun deleteFeature(id: String): Result<Unit>
}
```

#### 3. Define Use Cases

```kotlin
// domain/src/main/java/fr/benchaabane/riyadhair/domain/feature/usecases/GetFeaturesUseCase.kt
class GetFeaturesUseCase @Inject constructor(
    private val featureRepository: FeatureRepository
) {
    suspend operator fun invoke(): Result<List<Feature>> {
        return try {
            val features = featureRepository.getFeatures()
            Result.success(features)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

#### 4. Implement Data Layer

Create DTOs:
```kotlin
// data/src/main/java/fr/benchaabane/riyadhair/data/feature/api/FeatureDto.kt
@Serializable
data class FeatureDto(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean
)
```

Create API Service:
```kotlin
// data/src/main/java/fr/benchaabane/riyadhair/data/feature/api/FeatureService.kt
interface FeatureService {
    @GET("features")
    suspend fun getFeatures(): List<FeatureDto>
    
    @GET("features/{id}")
    suspend fun getFeature(@Path("id") id: String): FeatureDto
}
```

Create Database Entity:
```kotlin
// data/src/main/java/fr/benchaabane/riyadhair/data/feature/dao/FeatureEntity.kt
@Entity(tableName = "features")
data class FeatureEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean
)
```

Create DAO:
```kotlin
// data/src/main/java/fr/benchaabane/riyadhair/data/feature/dao/FeatureDao.kt
@Dao
interface FeatureDao {
    @Query("SELECT * FROM features")
    suspend fun getFeatures(): List<FeatureEntity>
    
    @Query("SELECT * FROM features WHERE id = :id")
    suspend fun getFeature(id: String): FeatureEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeature(feature: FeatureEntity)
    
    @Delete
    suspend fun deleteFeature(feature: FeatureEntity)
}
```

Create Mappers:
```kotlin
// data/src/main/java/fr/benchaabane/riyadhair/data/feature/mappers/FeatureMappers.kt
internal fun FeatureDto.toDomain(): Feature = Feature(
    id = id,
    name = name,
    description = description,
    isActive = isActive
)

internal fun FeatureEntity.toDomain(): Feature = Feature(
    id = id,
    name = name,
    description = description,
    isActive = isActive
)

internal fun Feature.toEntity(): FeatureEntity = FeatureEntity(
    id = id,
    name = name,
    description = description,
    isActive = isActive
)
```

Implement Repository:
```kotlin
// data/src/main/java/fr/benchaabane/riyadhair/data/feature/repositories/FeatureRepositoryImpl.kt
@Singleton
class FeatureRepositoryImpl @Inject constructor(
    private val featureService: FeatureService,
    private val featureDao: FeatureDao,
    private val networkBoundResource: NetworkBoundResource
) : FeatureRepository {
    
    override suspend fun getFeatures(): List<Feature> {
        return networkBoundResource.query(
            query = { featureDao.getFeatures().map { it.toDomain() } },
            fetch = { featureService.getFeatures() },
            saveFetchResult = { features ->
                featureDao.insertFeatures(features.map { it.toEntity() })
            },
            shouldFetch = { true }
        )
    }
    
    // Implement other methods...
}
```

#### 5. Implement Presentation Layer

Create ViewModel:
```kotlin
// presentation/src/main/java/fr/benchaabane/riyadhair/presentation/feature/FeatureViewModel.kt
@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val getFeaturesUseCase: GetFeaturesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<FeatureUiState>(FeatureUiState.Loading)
    val uiState: StateFlow<FeatureUiState> = _uiState.asStateFlow()
    
    init {
        loadFeatures()
    }
    
    private fun loadFeatures() {
        viewModelScope.launch {
            _uiState.value = FeatureUiState.Loading
            
            getFeaturesUseCase().fold(
                onSuccess = { features ->
                    _uiState.value = FeatureUiState.Success(features)
                },
                onFailure = { error ->
                    _uiState.value = FeatureUiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}

sealed class FeatureUiState {
    object Loading : FeatureUiState()
    data class Success(val features: List<Feature>) : FeatureUiState()
    data class Error(val message: String) : FeatureUiState()
}
```

Create UI Screen:
```kotlin
// presentation/src/main/java/fr/benchaabane/riyadhair/presentation/feature/FeatureScreen.kt
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState) {
        is FeatureUiState.Loading -> {
            CircularProgressIndicator()
        }
        is FeatureUiState.Success -> {
            FeatureList(features = uiState.features)
        }
        is FeatureUiState.Error -> {
            ErrorMessage(message = uiState.message)
        }
    }
}

@Composable
private fun FeatureList(features: List<Feature>) {
    LazyColumn {
        items(features) { feature ->
            FeatureCard(feature = feature)
        }
    }
}
```

#### 6. Add Navigation

Update navigation routes:
```kotlin
// presentation/src/main/java/fr/benchaabane/riyadhair/presentation/navigation/NavigationRoutes.kt
object NavigationRoutes {
    // ... existing routes
    const val FEATURES = "features"
}
```

Add to navigation graph:
```kotlin
// presentation/src/main/java/fr/benchaabane/riyadhair/presentation/navigation/NavigationGraph.kt
composable(NavigationRoutes.FEATURES) {
    FeatureScreen()
}
```

#### 7. Add Dependency Injection

Update repository module:
```kotlin
// app/src/main/java/fr/benchaabane/riyadhair/di/RepositoryModule.kt
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // ... existing bindings
    
    @Binds
    abstract fun bindFeatureRepository(
        featureRepositoryImpl: FeatureRepositoryImpl
    ): FeatureRepository
}
```

Update network module:
```kotlin
// app/src/main/java/fr/benchaabane/riyadhair/di/NetworkModule.kt
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // ... existing services
    
    @Provides
    @Singleton
    fun provideFeatureService(retrofit: Retrofit): FeatureService {
        return retrofit.create(FeatureService::class.java)
    }
}
```

### Package Naming Convention

For a feature named "Feature":

```
domain/feature/
├── models/
│   └── Feature.kt
├── repositories/
│   └── FeatureRepository.kt
└── usecases/
    ├── GetFeatureUseCase.kt
    ├── CreateFeatureUseCase.kt
    └── UpdateFeatureUseCase.kt

data/feature/
├── api/
│   ├── FeatureDto.kt
│   └── FeatureService.kt
├── dao/
│   ├── FeatureDao.kt
│   └── FeatureEntity.kt
├── mappers/
│   └── FeatureMappers.kt
└── repositories/
    └── FeatureRepositoryImpl.kt

presentation/feature/
├── FeatureScreen.kt
├── FeatureViewModel.kt
└── components/
    ├── FeatureCard.kt
    └── FeatureForm.kt
```

## Architecture Rules and Constraints

### 1. Dependency Direction

```
Presentation → Domain ← Data
     ↓           ↑       ↑
     └───────────┴───────┘
           Core
```

- **Domain** has no dependencies on other layers
- **Data** depends on **Domain**
- **Presentation** depends on **Domain**
- **Core** can be used by any layer

### 2. Module Dependencies

```kotlin
// build.gradle.kts dependencies
dependencies {
    // Domain depends on Core
    implementation(project(":core"))
    
    // Data depends on Domain and Core
    implementation(project(":domain"))
    implementation(project(":core"))
    
    // Presentation depends on Domain, Design System, and Core
    implementation(project(":domain"))
    implementation(project(":design_system"))
    implementation(project(":core"))
    
    // App depends on all modules
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":design_system"))
    implementation(project(":core"))
}
```

### 3. Data Flow

```
UI Action → ViewModel → UseCase → Repository → DataSource
    ↑                                 ↓
    └─────────── State ←──────────────┘
```

### 4. State Management

- Use `StateFlow` for reactive state
- ViewModels manage UI state
- Use cases handle business logic
- Repositories manage data operations

### 5. Error Handling

- Use `Result<T>` for operations that can fail
- Handle errors at the appropriate layer
- Provide meaningful error messages to users
- Log errors for debugging

## Testing Strategy

### 1. Unit Tests

- **Domain**: Test use cases and business logic
- **Data**: Test repositories, mappers, and data sources
- **Presentation**: Test ViewModels and UI logic

### 2. Integration Tests

- Test data flow between layers
- Test repository implementations
- Test API integrations

### 3. UI Tests

- Test user interactions
- Test navigation flows
- Test UI state changes

## Best Practices

### 1. Code Organization

- Keep packages focused and cohesive
- Use descriptive names for classes and functions
- Follow single responsibility principle
- Group related functionality together

### 2. Error Handling

- Use sealed classes for error states
- Provide meaningful error messages
- Handle errors gracefully
- Log errors appropriately

### 3. Performance

- Use pagination for large datasets
- Implement proper caching strategies
- Optimize database queries
- Use lazy loading where appropriate

### 4. Security

- Validate input data
- Sanitize user inputs
- Use secure network communication
- Implement proper authentication

### 5. Accessibility

- Provide content descriptions
- Support screen readers
- Use semantic markup
- Test with accessibility tools

## Common Patterns

### 1. Repository Pattern

```kotlin
interface Repository<T> {
    suspend fun get(id: String): T?
    suspend fun getAll(): List<T>
    suspend fun save(item: T): Result<T>
    suspend fun delete(id: String): Result<Unit>
}
```

### 2. Use Case Pattern

```kotlin
class UseCase<in P, R> @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(parameters: P): Result<R> {
        // Business logic implementation
    }
}
```

### 3. ViewModel Pattern

```kotlin
@HiltViewModel
class ViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()
    
    fun loadData() {
        viewModelScope.launch {
            // Implementation
        }
    }
}
```

### 4. Network-Bound Resource Pattern

```kotlin
class NetworkBoundResource<ResultType, RequestType> {
    suspend fun query(
        query: suspend () -> ResultType,
        fetch: suspend () -> RequestType,
        saveFetchResult: suspend (RequestType) -> Unit,
        shouldFetch: (ResultType) -> Boolean
    ): ResultType {
        // Implementation
    }
}
```

## Resources

### Documentation
- [Clean Architecture Guide](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt Dependency Injection](https://dagger.dev/hilt/)

### Project References
- See `HowToUnitTest.md` for testing guidelines
- See `HowToQuality.md` for quality tools
- Check existing feature implementations for examples
- Review test files for implementation patterns

