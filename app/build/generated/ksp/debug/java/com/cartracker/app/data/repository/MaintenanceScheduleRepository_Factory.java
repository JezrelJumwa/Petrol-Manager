package com.cartracker.app.data.repository;

import com.cartracker.app.data.dao.MaintenanceScheduleDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MaintenanceScheduleRepository_Factory implements Factory<MaintenanceScheduleRepository> {
  private final Provider<MaintenanceScheduleDao> maintenanceScheduleDaoProvider;

  public MaintenanceScheduleRepository_Factory(
      Provider<MaintenanceScheduleDao> maintenanceScheduleDaoProvider) {
    this.maintenanceScheduleDaoProvider = maintenanceScheduleDaoProvider;
  }

  @Override
  public MaintenanceScheduleRepository get() {
    return newInstance(maintenanceScheduleDaoProvider.get());
  }

  public static MaintenanceScheduleRepository_Factory create(
      Provider<MaintenanceScheduleDao> maintenanceScheduleDaoProvider) {
    return new MaintenanceScheduleRepository_Factory(maintenanceScheduleDaoProvider);
  }

  public static MaintenanceScheduleRepository newInstance(
      MaintenanceScheduleDao maintenanceScheduleDao) {
    return new MaintenanceScheduleRepository(maintenanceScheduleDao);
  }
}
