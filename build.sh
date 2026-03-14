#!/bin/zsh

# Petrol Manager Build Script
# Use this to build since Android Studio has jmod/jlink issues on M4

set -e

echo "🚗 Building Petrol Manager..."

# Stop any running Gradle daemons
./gradlew --stop > /dev/null 2>&1 || true

case "${1:-debug}" in
  clean)
    echo "🧹 Cleaning..."
    ./gradlew clean
    ;;
  debug|d)
    echo "🔨 Building Debug APK..."
    ./gradlew assembleDebug
    echo "✅ Debug APK: app/build/outputs/apk/debug/app-debug.apk"
    ;;
  release|r)
    echo "🔨 Building Release APK..."
    ./gradlew assembleRelease
    echo "✅ Release APK: app/build/outputs/apk/release/app-release.apk"
    ;;
  install|i)
    echo "📱 Installing to device..."
    ./gradlew installDebug
    echo "✅ App installed"
    ;;
  test|t)
    echo "🧪 Running tests..."
    ./gradlew test
    ;;
  *)
    echo "Usage: ./build.sh [clean|debug|release|install|test]"
    echo "  clean   - Clean build artifacts"
    echo "  debug   - Build debug APK (default)"
    echo "  release - Build release APK"
    echo "  install - Build and install debug APK to connected device"
    echo "  test    - Run unit tests"
    exit 1
    ;;
esac
