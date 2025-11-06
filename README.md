# Car Service Tracker

A modern Android application for tracking vehicle maintenance, service history, parts replacement, and expenses. Built with the latest Android development technologies including Jetpack Compose, Room Database, and Hilt dependency injection.

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
│       └── VehicleRepository.kt
├── di/                         # Dependency injection modules
│   └── DatabaseModule.kt
├── presentation/
│   ├── screens/                # UI screens
│   │   └── vehicle/
│   │       ├── VehicleListScreen.kt
│   │       ├── VehicleListViewModel.kt
│   │       ├── AddVehicleScreen.kt
│   │       ├── AddVehicleViewModel.kt
│   │       └── VehicleDetailScreen.kt
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
   cd /Users/jezreljumwa/IdeaProjects/Personal/PetrolManager
   ```

2. **Open in Android Studio**:
   - File → Open → Select the PetrolManager directory
   - Wait for Gradle sync to complete

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run on device/emulator**:
   - Connect an Android device or start an emulator
   - Click Run button or:
   ```bash
   ./gradlew installDebug
   ```

### Configuration

#### Minimum SDK Requirements
- **minSdk**: 26 (Android 8.0 Oreo)
- **targetSdk**: 34 (Android 14)
- **compileSdk**: 34

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

### Version 1.0.0 (Current)
- ✅ Initial release
- ✅ Vehicle management (Add, List, Detail)
- ✅ Database schema with all entities
- ✅ Modern Compose UI with Material 3
- ✅ MVVM architecture with Hilt DI
- ✅ Navigation setup
- 🚧 Service history screens (structure ready)
- 🚧 Maintenance schedules (structure ready)
- 🚧 Expense tracking (structure ready)
- 🚧 Reminder notifications (planned)

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
