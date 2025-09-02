# RiyadhAir - Android Application

## 🚀 Project Overview

RiyadhAir is a modern Android application built with Clean Architecture principles, designed to provide a comprehensive flight booking and travel management experience. The application showcases best practices in Android development, including Jetpack Compose, MVVM architecture, and comprehensive testing strategies.

## ✨ Features

### 🎯 Core Functionality
- **Flight Search & Booking**: Comprehensive flight search with filtering and booking capabilities
- **User Account Management**: User profiles, loyalty programs, and preferences
- **Travel Offers**: Special deals and promotional offers for destinations
- **Partner Integration**: Travel partner services and discounts
- **Reservation Management**: Booking history and reservation management
- **MRZ Scanning**: Passport and travel document scanning using ML Kit

### 🎨 User Experience
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Adaptive Design**: Responsive layouts for different screen sizes
- **RTL Support**: Full right-to-left language support
- **Accessibility**: Comprehensive accessibility features and screen reader support

### 🔧 Technical Features
- **Offline Support**: Local caching and offline-first approach
- **Real-time Updates**: Live flight status and availability updates
- **Secure Authentication**: Hilt-based dependency injection and secure data handling
- **Performance Optimization**: Efficient pagination and lazy loading

## 🏗️ Architecture

### Clean Architecture Implementation
The project follows Clean Architecture principles with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│              (UI, ViewModels, Navigation)                  │
├─────────────────────────────────────────────────────────────┤
│                     Domain Layer                            │
│           (Business Logic, Use Cases, Models)              │
├─────────────────────────────────────────────────────────────┤
│                      Data Layer                             │
│         (Repositories, API, Database, Mappers)             │
├─────────────────────────────────────────────────────────────┤
│                      Core Module                            │
│              (Utilities, Extensions, Common)               │
└─────────────────────────────────────────────────────────────┘
```

### Module Structure
- **`:app`** - Application entry point and DI setup
- **`:presentation`** - UI layer with Jetpack Compose
- **`:domain`** - Business logic and domain models
- **`:data`** - Data access and external integrations
- **`:core`** - Shared utilities and common functionality
- **`:design_system`** - UI components and design tokens

### Key Architectural Patterns
- **MVVM**: Model-View-ViewModel pattern for UI logic
- **Repository Pattern**: Abstract data access layer
- **Use Case Pattern**: Business logic encapsulation
- **Dependency Injection**: Hilt for dependency management
- **Unidirectional Data Flow**: State management with StateFlow

## 🛠️ Technology Stack

### Core Technologies
- **Language**: Kotlin 2.2.10
- **Minimum SDK**: API 26 (Android 8.0)
- **Target SDK**: API 36 (Android 15)
- **Build System**: Gradle 8.13 with Kotlin DSL

### UI Framework
- **Jetpack Compose**: Modern declarative UI toolkit
- **Material Design 3**: Latest Material Design components
- **Compose Navigation**: Type-safe navigation framework
- **Compose Paging**: Efficient list pagination

### Architecture Components
- **ViewModel**: UI state management
- **StateFlow**: Reactive data streams
- **Room**: Local database with Kotlin coroutines
- **Hilt**: Dependency injection framework
- **Retrofit**: HTTP client for API communication

### Testing & Quality
- **JUnit 4**: Unit testing framework
- **Mockk**: Mocking library for Kotlin
- **Kluent**: Fluent assertion library
- **Turbine**: Flow testing utilities
- **JaCoCo**: Code coverage analysis
- **Detekt**: Static code analysis
- **Konsist**: Architectural consistency validation

### Additional Libraries
- **Coil**: Image loading and caching
- **CameraX**: Camera functionality
- **ML Kit**: Text recognition for MRZ scanning
- **Filament**: 3D rendering capabilities
- **Kotlinx Serialization**: JSON serialization

## 📱 Screenshots

*[Screenshots will be added here]*

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK API 26+
- Gradle 8.13+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/RiyadhAir.git
   cd RiyadhAir
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned repository and select it

3. **Sync and Build**
   - Wait for Gradle sync to complete
   - Build the project (Build → Make Project)

4. **Run the application**
   - Connect an Android device or start an emulator
   - Click the Run button or press Shift+F10

### Build Variants
- **Debug**: Development build with debugging enabled
- **Release**: Production build with optimizations

## 🧪 Testing

### Running Tests
```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :app:test
./gradlew :domain:test
./gradlew :data:test
./gradlew :presentation:test

# Run tests with coverage
./gradlew :app:jacocoTestReport
./gradlew jacocoRootReport
```

### Test Structure
- **Unit Tests**: Located in `src/test/` directories
- **Test Coverage**: JaCoCo reports in `build/reports/jacoco/`
- **Test Results**: HTML reports in `build/reports/tests/`

## 🔍 Quality Assurance

### Code Quality Tools
- **Detekt**: Static code analysis and style enforcement
- **Konsist**: Architectural consistency validation
- **JaCoCo**: Code coverage measurement
- **Dokka**: API documentation generation

### Running Quality Checks
```bash
# Run Detekt analysis
./gradlew detekt

# Run Konsist tests
./gradlew :app:test --tests "*Konsist*"

# Generate documentation
./gradlew dokkaGenerate
```

### Quality Metrics
- **Code Coverage Target**: 80%+ overall coverage
- **Code Quality**: 0 critical Detekt issues
- **Architecture**: All Konsist tests passing
- **Documentation**: Comprehensive KDoc coverage

## 📚 Documentation

### How-To Guides
- **[HowToUnitTest.md](HowToUnitTest.md)** - Comprehensive guide for unit testing
- **[HowToArchitecture.md](HowToArchitecture.md)** - Project architecture and feature development
- **[HowToQuality.md](HowToQuality.md)** - Quality tools and best practices

### API Documentation
- Generated documentation available in `build/dokka/html/` after running Dokka

## 🏗️ Development Workflow

### Adding New Features
1. **Domain Layer**: Define models, repositories, and use cases
2. **Data Layer**: Implement repositories, API services, and mappers
3. **Presentation Layer**: Create ViewModels and UI components
4. **Testing**: Add comprehensive unit tests
5. **Documentation**: Update KDoc and relevant guides

### Code Standards
- Follow Kotlin coding conventions
- Use meaningful names for classes, functions, and variables
- Write comprehensive KDoc documentation
- Maintain test coverage above 80%
- Follow Clean Architecture principles

### Git Workflow
- Use descriptive commit messages
- Create feature branches for new development
- Ensure all tests pass before merging
- Run quality checks before committing

## 🔧 Configuration

### Environment Setup
- **API Configuration**: Configure in `NetworkModule.kt`
- **Database Configuration**: Settings in `DatabaseModule.kt`
- **Build Variants**: Configure in `build.gradle.kts` files

### Dependencies
- **Version Management**: Centralized in `gradle/libs.versions.toml`
- **Module Dependencies**: Configured in respective `build.gradle.kts` files
- **Plugin Management**: All plugins use version catalog references

## 📊 Performance

### Optimization Strategies
- **Lazy Loading**: Efficient pagination for large datasets
- **Image Caching**: Coil-based image optimization
- **Database Optimization**: Room with efficient queries
- **Network Caching**: Retrofit with OkHttp caching
- **Memory Management**: Proper lifecycle awareness

### Monitoring
- **Build Performance**: Gradle build scan integration
- **Runtime Performance**: Android Profiler integration
- **Memory Usage**: Memory leak detection and prevention

## 🔒 Security

### Security Features
- **Network Security**: HTTPS-only communication
- **Data Validation**: Input sanitization and validation
- **Secure Storage**: Encrypted local data storage
- **Authentication**: Secure user authentication flow

## 🌐 Internationalization

### Language Support
- **RTL Languages**: Full right-to-left support
- **Localization**: Multi-language resource files
- **Cultural Adaptation**: Region-specific content and formatting

## 🤝 Contributing

### Contribution Guidelines
1. Fork the repository
2. Create a feature branch
3. Follow coding standards and architecture principles
4. Add comprehensive tests
5. Update documentation
6. Submit a pull request

### Development Setup
- Follow the architecture guidelines in `HowToArchitecture.md`
- Ensure all quality checks pass
- Maintain test coverage standards
- Update relevant documentation

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Jetpack Compose Team**: For the modern UI toolkit
- **Android Architecture Components**: For architectural guidance
- **Kotlin Team**: For the excellent programming language
- **Open Source Community**: For the amazing libraries and tools

## 📞 Support

### Getting Help
- **Documentation**: Check the How-To guides first
- **Issues**: Report bugs and feature requests via GitHub Issues
- **Discussions**: Use GitHub Discussions for questions and ideas

### Contact
- **Project Maintainer**: [Fares Ben Chaabane]
- **Email**: [fares.benchaabane.pro@gmail.com]
- **GitHub**: [@fares-benchaabane]

---

**Built with ❤️ using modern Android development practices**

*Last updated: August 2025*

