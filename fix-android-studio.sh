#!/bin/zsh

# Fix Android Studio Build Issues on M4 Mac
# This script cleans up problematic Gradle caches

echo "🔧 Fixing Android Studio configuration for M4 Mac..."

# Stop any running Gradle daemons
echo "\n📛 Stopping Gradle daemons..."
./gradlew --stop

# Remove project-local .gradle directory
if [ -d ".gradle" ]; then
    echo "\n🗑️  Removing project .gradle directory..."
    rm -rf .gradle
fi

# Remove build directories
echo "\n🧹 Cleaning build directories..."
rm -rf app/build
rm -rf build

# Remove Android Studio's Gradle cache for this project
if [ -d ".idea" ]; then
    echo "\n🗑️  Cleaning Android Studio caches..."
    rm -rf .idea/caches
    rm -rf .idea/libraries
fi

# Clean system Gradle cache (optional - commented out by default)
# Uncomment if you want to do a full clean
# echo "\n🗑️  Cleaning system Gradle cache..."
# rm -rf ~/.gradle/caches

echo "\n✅ Done! Now do the following in Android Studio:"
echo ""
echo "1. File → Invalidate Caches → Invalidate and Restart"
echo ""
echo "2. After restart, go to:"
echo "   Settings → Build, Execution, Deployment → Build Tools → Gradle"
echo ""
echo "3. Set Gradle user home to: (leave blank or use ~/.gradle)"
echo "   CLEAR THIS: /Users/jezreljumwa/StudioProjects/Petrol-Manager/.gradle"
echo ""
echo "4. Verify Gradle JDK is set to:"
echo "   jbr-21 JetBrains Runtime 21.0.8 - aarch64"
echo "   Path: /Users/jezreljumwa/Library/Java/JavaVirtualMachines/jbrsdk_jcef-21.0.9/Contents/Home"
echo ""
echo "5. File → Sync Project with Gradle Files"
echo ""
echo "6. Build → Make Project (⌘F9)"
echo ""
