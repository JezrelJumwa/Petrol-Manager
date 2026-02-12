package com.cartracker.app.di;

import com.cartracker.app.data.dao.ServiceRecordDao;
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
public final class DatabaseModule_ProvideServiceRecordDaoFactory implements Factory<ServiceRecordDao> {
  private final Provider<CarTrackerDatabase> databaseProvider;

  public DatabaseModule_ProvideServiceRecordDaoFactory(
      Provider<CarTrackerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ServiceRecordDao get() {
    return provideServiceRecordDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideServiceRecordDaoFactory create(
      Provider<CarTrackerDatabase> databaseProvider) {
    return new DatabaseModule_ProvideServiceRecordDaoFactory(databaseProvider);
  }

  public static ServiceRecordDao provideServiceRecordDao(CarTrackerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideServiceRecordDao(database));
  }
}
