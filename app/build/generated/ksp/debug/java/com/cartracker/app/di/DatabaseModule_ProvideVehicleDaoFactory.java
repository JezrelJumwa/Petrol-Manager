package com.cartracker.app.di;

import com.cartracker.app.data.dao.VehicleDao;
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
public final class DatabaseModule_ProvideVehicleDaoFactory implements Factory<VehicleDao> {
  private final Provider<CarTrackerDatabase> databaseProvider;

  public DatabaseModule_ProvideVehicleDaoFactory(Provider<CarTrackerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public VehicleDao get() {
    return provideVehicleDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideVehicleDaoFactory create(
      Provider<CarTrackerDatabase> databaseProvider) {
    return new DatabaseModule_ProvideVehicleDaoFactory(databaseProvider);
  }

  public static VehicleDao provideVehicleDao(CarTrackerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideVehicleDao(database));
  }
}
