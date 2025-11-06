package com.cartracker.app.di;

import com.cartracker.app.data.dao.MileageLogDao;
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
public final class DatabaseModule_ProvideMileageLogDaoFactory implements Factory<MileageLogDao> {
  private final Provider<CarTrackerDatabase> databaseProvider;

  public DatabaseModule_ProvideMileageLogDaoFactory(Provider<CarTrackerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MileageLogDao get() {
    return provideMileageLogDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMileageLogDaoFactory create(
      Provider<CarTrackerDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMileageLogDaoFactory(databaseProvider);
  }

  public static MileageLogDao provideMileageLogDao(CarTrackerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMileageLogDao(database));
  }
}
