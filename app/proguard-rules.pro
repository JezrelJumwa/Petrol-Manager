# Add project specific ProGuard rules here.
-keep class com.cartracker.app.data.model.** { *; }
-keepclassmembers class * {
    @androidx.room.* <methods>;
}
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**
