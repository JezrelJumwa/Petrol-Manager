package com.cartracker.app.di;

import com.cartracker.app.data.dao.PartDao;
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
public final class DatabaseModule_ProvidePartDaoFactory implements Factory<PartDao> {
  private final Provider<CarTrackerDatabase> databaseProvider;

  public DatabaseModule_ProvidePartDaoFactory(Provider<CarTrackerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PartDao get() {
    return providePartDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePartDaoFactory create(
      Provider<CarTrackerDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePartDaoFactory(databaseProvider);
  }

  public static PartDao providePartDao(CarTrackerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePartDao(database));
  }
}
