# Android Studio Setup Guide for M4 Macs

This project is configured to work with Android Studio on M4 Macs, which have known issues with jmod/jlink operations.

## Quick Fix Steps

If Android Studio fails to build but `./build.sh` works:

### 1. Configure Gradle JDK

1. Open **Android Studio**
2. Go to: `File → Project Structure` (or press `⌘;`)
3. Select **SDK Location** in the left sidebar
4. Under **Gradle Settings**, set **Gradle JDK** to:
   - Option A: `JBR (JetBrains Runtime) version 21.x` (Recommended)
   - Option B: Browse and select: `/Users/[your-username]/Library/Java/JavaVirtualMachines/jbrsdk_jcef-21.0.9/Contents/Home`
5. Click **Apply** and **OK**

### 2. Invalidate Caches and Restart

1. Go to: `File → Invalidate Caches...`
2. Check all options:
   - ✅ Clear file system cache and Local History
   - ✅ Clear VCS Log caches and indexes
   - ✅ Clear downloaded shared indexes
3. Click **Invalidate and Restart**

### 3. Sync Project

After Android Studio restarts:
1. Go to: `File → Sync Project with Gradle Files`
2. Wait for sync to complete
3. Try building: `Build → Make Project` or press `⌘F9`

## Troubleshooting

### Build Still Fails?

#### Check Gradle Settings
Verify `gradle.properties` contains:
```properties
android.enableJdkWorkers=false
org.gradle.java.home=/Users/[username]/Library/Java/JavaVirtualMachines/jbrsdk_jcef-21.0.9/Contents/Home
```

#### Verify JDK Installation
Run in terminal:
```bash
/usr/libexec/java_home -V
```

Look for JBR (JetBrains Runtime) 21.x in the output.

#### Use Terminal as Fallback
```bash
# Clean and build
./build.sh clean
./build.sh debug

# Then sync in Android Studio
```

### Common Errors and Solutions

#### Error: "Could not resolve all dependencies"
**Solution:** 
1. Check internet connection
2. `File → Sync Project with Gradle Files`
3. If still failing: `./gradlew --refresh-dependencies`

#### Error: "Execution failed for task ':app:ksp...'"
**Solution:**
1. Clean project: `./build.sh clean`
2. Invalidate caches (see Step 2 above)
3. Rebuild project

#### Error: "jmod/jlink not found" or similar
**Solution:** 
This is the M4 Mac issue. Ensure `android.enableJdkWorkers=false` in `gradle.properties`

#### Android Studio Freezes During Build
**Solution:**
1. Increase memory: `Preferences → Appearance & Behavior → System Settings → Memory Settings`
2. Set IDE Maximum Heap Size to at least 4096 MB
3. Restart Android Studio

## Project Configuration Details

### Gradle Properties (gradle.properties)
```properties
# M4 Mac specific settings
android.enableJdkWorkers=false
org.gradle.workers.max=4
org.gradle.parallel=true
org.gradle.jvmargs=-Xmx4096m

# JDK path
org.gradle.java.home=/Users/[username]/Library/Java/JavaVirtualMachines/jbrsdk_jcef-21.0.9/Contents/Home
```

### Build Script Alternative
If Android Studio continues to have issues, you can develop using:
- **Code editing**: Android Studio (works perfectly)
- **Building**: Terminal with `./build.sh`
- **Running**: `./build.sh install` to install on device

## Additional Resources

- **Android Studio JBR Issues**: https://issuetracker.google.com/issues/
- **Gradle JVM Arguments**: https://docs.gradle.org/current/userguide/build_environment.html

## Verification

After setup, verify everything works:

```bash
# Terminal build (should work)
./build.sh debug

# Android Studio build
# In Android Studio: Build → Make Project (⌘F9)
```

Both should succeed without errors.

## Support

If you've followed all steps and still have issues:
1. Check the error message in Android Studio's Build Output
2. Try building with `./gradlew assembleDebug --stacktrace` for detailed error info
3. Post the error in the project issues
