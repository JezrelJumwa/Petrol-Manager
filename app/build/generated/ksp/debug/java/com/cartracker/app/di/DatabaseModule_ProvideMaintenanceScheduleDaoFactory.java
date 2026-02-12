package com.cartracker.app.di;

import com.cartracker.app.data.dao.MaintenanceScheduleDao;
import com.cartracker.app.data.database.CarTrackerDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideMaintenanceScheduleDaoFactory implements Factory<MaintenanceScheduleDao> {
  private final Provider<CarTrackerDatabase> databaseProvider;

  public DatabaseModule_ProvideMaintenanceScheduleDaoFactory(
      Provider<CarTrackerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MaintenanceScheduleDao get() {
    return provideMaintenanceScheduleDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMaintenanceScheduleDaoFactory create(
      Provider<CarTrackerDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMaintenanceScheduleDaoFactory(databaseProvider);
  }

  public static MaintenanceScheduleDao provideMaintenanceScheduleDao(CarTrackerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMaintenanceScheduleDao(database));
  }
}
