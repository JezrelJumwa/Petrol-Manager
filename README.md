# Car Service Tracker

[![Android CI](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/android-ci.yml/badge.svg)](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/android-ci.yml)
[![CodeQL](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/codeql.yml/badge.svg)](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/codeql.yml)
[![Security Scan](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/security-scan.yml/badge.svg)](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/security-scan.yml)
[![Code Quality](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/code-quality.yml/badge.svg)](https://github.com/JezrelJumwa/Petrol-Manager/actions/workflows/code-quality.yml)

A modern Android application for tracking vehicle maintenance, service history, parts replacement, and expenses. Built with the latest Android development technologies including Jetpack Compose, Room Database, and Hilt dependency injection.

## Security & CI/CD

This project ships a layered, open-source security and CI pipeline (GitHub Actions). Full details in [SECURITY.md](SECURITY.md).

- **SAST:** CodeQL (`java-kotlin`) + MobSF `mobsfscan` → results in the GitHub Security tab
- **APK analysis:** MobSF full static scan of the built APK (Docker, REST API)
- **Secrets:** gitleaks across full git history
- **Dependencies:** Dependabot + dependency-review + Gradle dependency submission
- **Quality:** detekt, ktlint, Android Lint
- **Build:** debug build, lint and unit tests on every push/PR
- **DAST:** MobSF dynamic analysis is a documented local/manual step (CI runners can't host an instrumented emulator) — see [SECURITY.md](SECURITY.md)

## Features

### Core Features
- **Multi-Vehicle Management**: Track multiple vehicles with detailed information
  - Make, model, year, license plate, VIN
  - Current mileage tracking
  - Vehicle notes and purchase information

- **Service History**: Comprehensive service record tracking
  - Service type (Oil Change, Brake Service, Tire Rotation, etc.)
  - Service date and mileage
  - Cost tracking
  - Shop/mechanic information
  - Service notes
  - Next service due date/mileage

- **Parts Tracking**: Detailed parts replacement records
  - Part name and part number
  - Brand and quantity
  - Cost per part
  - Warranty information
  - Automatic linking to service records

- **Maintenance Schedules**: Proactive maintenance planning
  - Customizable maintenance intervals (miles or months)
  - Automatic next due calculation
  - Upcoming maintenance reminders
  - Multiple schedule types per vehicle

- **Mileage Logging**: Track vehicle usage over time
  - Date-stamped mileage entries
  - Automatic vehicle mileage updates
  - Historical mileage tracking

- **Expense Tracking**: Complete financial overview
  - Multiple expense categories (Fuel, Insurance, Registration, Parking, Tolls)
  - Date and cost tracking
  - Vendor information
  - Total expense calculations
  - Date-range filtering

### Advanced Features (Database Ready)
- Maintenance reminder notifications
- Service predictions based on usage patterns
- Cost analysis and reporting
- Export/import data functionality
- Multi-vehicle comparison
- Search and filter capabilities
- Data backup and restore

## Technology Stack

### Architecture
- **MVVM** (Model-View-ViewModel) with Clean Architecture principles
- **Repository Pattern** for data abstraction
- **Unidirectional Data Flow** with StateFlow

### Core Technologies
- **Kotlin**: 100% Kotlin codebase
- **Jetpack Compose**: Modern declarative UI framework
- **Material 3**: Latest Material Design components
- **Coroutines & Flow**: Asynchronous programming

### Data & Persistence
- **Room Database**: Local SQLite database with type-safe queries
- **Foreign Key Relationships**: Proper data integrity
- **Database Indexing**: Optimized query performance

### Dependency Injection
- **Hilt**: Compile-time dependency injection
- **ViewModel Integration**: Scoped dependency management

### Navigation
- **Navigation Compose**: Type-safe navigation between screens

## Project Structure

```
app/src/main/java/com/cartracker/app/
├── data/
│   ├── dao/                    # Room DAOs for database access
│   │   ├── VehicleDao.kt
│   │   ├── ServiceRecordDao.kt
│   │   ├── PartDao.kt
│   │   ├── MaintenanceScheduleDao.kt
│   │   ├── MileageLogDao.kt
│   │   └── ExpenseDao.kt
│   ├── database/               # Room database definition
│   │   └── CarTrackerDatabase.kt
│   ├── model/                  # Room entities
│   │   ├── VehicleEntity.kt
│   │   ├── ServiceRecordEntity.kt
│   │   ├── PartEntity.kt
│   │   ├── MaintenanceScheduleEntity.kt
│   │   ├── MileageLogEntity.kt
│   │   └── ExpenseEntity.kt
│   └── repository/             # Repository layer
│       ├── VehicleRepository.kt
│       ├── ServiceRecordRepository.kt
│       ├── PartRepository.kt
│       ├── MaintenanceScheduleRepository.kt
│       ├── MileageLogRepository.kt
│       └── ExpenseRepository.kt
├── di/                         # Dependency injection modules
│   └── DatabaseModule.kt
├── presentation/
│   ├── screens/                # UI screens
│   │   ├── vehicle/
│   │   │   ├── VehicleListScreen.kt
│   │   │   ├── VehicleListViewModel.kt
│   │   │   ├── AddVehicleScreen.kt
│   │   │   ├── AddVehicleViewModel.kt
│   │   │   ├── VehicleDetailScreen.kt
│   │   │   └── VehicleDetailViewModel.kt
│   │   ├── service/
│   │   │   ├── ServiceListScreen.kt
│   │   │   ├── ServiceListViewModel.kt
│   │   │   ├── AddServiceScreen.kt
│   │   │   └── AddServiceViewModel.kt
│   │   ├── parts/
│   │   │   ├── PartsListScreen.kt
│   │   │   ├── PartsListViewModel.kt
│   │   │   ├── AddPartScreen.kt
│   │   │   └── AddPartViewModel.kt
│   │   ├── maintenance/
│   │   │   ├── MaintenanceListScreen.kt
│   │   │   ├── MaintenanceListViewModel.kt
│   │   │   ├── AddMaintenanceScreen.kt
│   │   │   └── AddMaintenanceViewModel.kt
│   │   ├── mileage/
│   │   │   ├── MileageLogScreen.kt
│   │   │   └── MileageLogViewModel.kt
│   │   └── expense/
│   │       ├── ExpenseListScreen.kt
│   │       ├── ExpenseListViewModel.kt
│   │       ├── AddExpenseScreen.kt
│   │       └── AddExpenseViewModel.kt
│   ├── navigation/             # Navigation setup
│   │   ├── Screen.kt
│   │   └── CarTrackerNavHost.kt
│   ├── components/             # Reusable UI components
│   └── theme/                  # App theming
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── MainActivity.kt             # Main activity
└── CarTrackerApplication.kt   # Application class
```

## Database Schema

### Tables
1. **vehicles**: Core vehicle information
2. **service_records**: Service history with foreign key to vehicles
3. **parts**: Parts used in services with foreign key to service_records
4. **maintenance_schedules**: Maintenance schedules with foreign key to vehicles
5. **mileage_logs**: Mileage tracking with foreign key to vehicles
6. **expenses**: General expenses with foreign key to vehicles

### Key Relationships
- One vehicle can have many service records
- One service record can have many parts
- One vehicle can have many maintenance schedules
- One vehicle can have many mileage logs
- One vehicle can have many expenses
- CASCADE deletion ensures data integrity

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK with minimum API 26 (Android 8.0)
- Gradle 8.2+

### Building the Project

1. **Clone the repository** (or open existing directory):
   ```bash
   cd /Users/jezreljumwa/StudioProjects/Petrol-Manager
   ```

2. **Open in Android Studio**:
   - File → Open → Select the Petrol-Manager directory
   - Wait for Gradle sync to complete
   - **Note for M4 Macs**: If builds fail, see [ANDROID_STUDIO_SETUP.md](ANDROID_STUDIO_SETUP.md)

3. **Using the Build Script** (Recommended for M4 Macs):
   The project includes a convenient build script to avoid jmod/jlink issues:
   
   ```bash
   # Clean build artifacts
   ./build.sh clean
   
   # Build debug APK (default)
   ./build.sh debug
   # or simply
   ./build.sh
   
   # Build release APK
   ./build.sh release
   
   # Build and install to connected device
   ./build.sh install
   
   # Run unit tests
   ./build.sh test
   ```

4. **Manual Gradle Commands**:
   ```bash
   # Build the project
   ./gradlew build
   
   # Install on device/emulator
   ./gradlew installDebug
   ```

### Configuration

#### Minimum SDK Requirements
- **minSdk**: 26 (Android 8.0 Oreo)
- **targetSdk**: 34 (Android 14)
- **compileSdk**: 36

#### ⚠️ Android Studio Build Issues on M4 Macs (KNOWN LIMITATION)

**IMPORTANT**: Android Studio **CANNOT** build this project on M4 Macs due to fundamental
jmod/jlink incompatibility with ARM architecture. This is a known Android Gradle Plugin
issue affecting all M4 Mac developers.

**Workaround - Use Terminal Builds:**
```bash
./build.sh debug      # Build debug APK
./build.sh release    # Build release APK  
./build.sh install    # Install to device
./build.sh test       # Run tests
```

**Android Studio is still useful for:**
- Code editing, refactoring, navigation
- UI preview and design
- Git operations and version control
- Debugging (attach after installing via `./build.sh install`)

**Why this happens:**
- Android Gradle Plugin requires jmod/jlink transforms when building through Android Studio
- The jmod tool doesn't work on M4 (ARM) architecture
- `android.enableJdkWorkers=false` only helps terminal builds, not Android Studio
- Tested with AGP 8.13, 8.2 - all fail with same jmod error

**When will this be fixed?**
When Google updates AGP or Oracle/Apple fix jmod on ARM. No ETA available.

#### Permissions
The app requires the following permissions (automatically handled):
- `POST_NOTIFICATIONS`: For maintenance reminders
- `SCHEDULE_EXACT_ALARM`: For precise reminder timing
- `VIBRATE`: For notification alerts

## Usage Guide

### Adding Your First Vehicle
1. Launch the app
2. Tap the floating "+" button
3. Fill in vehicle details:
   - Make (e.g., Toyota)
   - Model (e.g., Camry)
   - Year (e.g., 2020)
   - License Plate
   - Current Mileage
   - Optional: VIN, Notes
4. Tap "Save Vehicle"

### Recording Service
1. Select a vehicle from the list
2. Navigate to "Service History"
3. Tap "Add Service"
4. Enter service details and parts used
5. Save to track the service

### Setting Up Maintenance Schedules
1. Select a vehicle
2. Go to "Maintenance Schedule"
3. Add schedules for regular maintenance:
   - Oil Change: Every 5,000 miles or 6 months
   - Tire Rotation: Every 7,500 miles
   - Brake Inspection: Every 12 months
4. Enable reminders to get notifications

### Tracking Expenses
1. Select a vehicle
2. Navigate to "Expenses"
3. Add expenses by category:
   - Fuel
   - Insurance
   - Registration
   - Parking/Tolls
4. View total costs and analyze spending

## Future Enhancements

### Planned Features
- [ ] Dashboard with analytics and charts
- [ ] Export data to CSV/PDF
- [ ] Cloud backup integration
- [ ] Photo attachments for services/receipts
- [ ] Fuel economy tracking
- [ ] Trip logging
- [ ] Service shop directory integration
- [ ] Reminder notifications (WorkManager integration)
- [ ] Dark mode (already supported by theme)
- [ ] Multi-language support
- [ ] Wear OS companion app

### Advanced Analytics
- [ ] Cost per mile calculations
- [ ] Service frequency analysis
- [ ] Predictive maintenance suggestions
- [ ] Comparison with similar vehicles
- [ ] Resale value estimation

## Development Notes

### Adding New Screens
1. Create ViewModel in `presentation/screens/{feature}/`
2. Create Composable screen in same directory
3. Add navigation route in `Screen.kt`
4. Wire up in `CarTrackerNavHost.kt`

### Adding New Database Entities
1. Create entity in `data/model/`
2. Create DAO in `data/dao/`
3. Add to `CarTrackerDatabase.kt`
4. Increment database version
5. Create repository if needed

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Dependencies

### Key Libraries
- `androidx.compose`: Jetpack Compose UI toolkit
- `androidx.room`: Local database
- `com.google.dagger.hilt`: Dependency injection
- `androidx.navigation.compose`: Navigation
- `androidx.work`: Background task scheduling (future)
- `kotlinx.coroutines`: Asynchronous programming

### Version Information
- Kotlin: 1.9.22
- Compose BOM: 2024.02.00
- Room: 2.6.1
- Hilt: 2.50
- Navigation: 2.7.7

## License

This project is for personal use. Feel free to modify and adapt to your needs.

## Contributing

This is a personal project, but suggestions and improvements are welcome!

## Support

For issues or questions:
1. Check existing documentation
2. Review code comments
3. Check Android Studio build errors

## Changelog

### Version 1.1.0 (Current)
- **Bug fix**: `loadTotalCost` / `loadTotalExpenses` dead-code bug — totals always displayed 0.00 (fixed by computing totals inside the `collect` lambda)
- **Bug fix**: Currency symbol on service and expense summary cards now uses `formatAmount()` instead of raw `String.format`
- **Bug fix**: `AddMaintenanceScreen` dropdown no longer detaches from its text field (`DropdownMenu` → `ExposedDropdownMenu`)
- **Bug fix**: Removed no-op `LaunchedEffect` block in `AddVehicleScreen`
- **Bug fix**: `collectAsState()` → `collectAsStateWithLifecycle()` in `ServiceListScreen` and `ExpenseListScreen`
- **Feature**: `VehicleDetailScreen` fully implemented — was a stub; now shows vehicle profile card (make/model/year/mileage/VIN/notes) with dynamic top bar title via `VehicleDetailViewModel`
- **Feature**: Delete confirmation dialogs added to all 6 list screens (Vehicle, Service, Expense, Maintenance, Mileage, Parts)
- **Feature**: `AddPartScreen` + `AddPartViewModel` — full Add Part UI with service record picker, part fields, currency selector, and warranty input
- **Feature**: `PartRepository.insertPart()` method added
- **Feature**: `AddPart` navigation route added; `PartsListScreen` now has a FAB to navigate to `AddPartScreen`

### Version 1.0.0
- Initial release
- Vehicle management (Add, List, Detail)
- Database schema with all entities
- Modern Compose UI with Material 3
- MVVM architecture with Hilt DI
- Navigation setup
- Service history tracking (List + Add screens)
- Expense tracking (List + Add screens)
- Build script for M4 Mac compatibility
- Maintenance schedules (List + Add)
- Mileage logging UI
- Parts tracking UI
- Edit/Delete functionality for all entities
- Reminder notifications (planned)

## Notes

This app replaces the old PetrolManager Java codebase with a completely modern solution. The database schema is designed to be extensible and supports all planned features. The architecture follows Android best practices and is ready for future enhancements.

## Building for Production

```bash
# Generate release APK
./gradlew assembleRelease

# Generate release AAB for Play Store
./gradlew bundleRelease
```

Remember to configure signing keys in `app/build.gradle.kts` for production builds.
