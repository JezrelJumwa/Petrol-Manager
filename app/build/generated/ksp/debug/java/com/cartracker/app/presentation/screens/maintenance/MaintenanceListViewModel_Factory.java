package com.cartracker.app.presentation.screens.maintenance;

import com.cartracker.app.data.repository.MaintenanceScheduleRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class MaintenanceListViewModel_Factory implements Factory<MaintenanceListViewModel> {
  private final Provider<MaintenanceScheduleRepository> maintenanceScheduleRepositoryProvider;

  public MaintenanceListViewModel_Factory(
      Provider<MaintenanceScheduleRepository> maintenanceScheduleRepositoryProvider) {
    this.maintenanceScheduleRepositoryProvider = maintenanceScheduleRepositoryProvider;
  }

  @Override
  public MaintenanceListViewModel get() {
    return newInstance(maintenanceScheduleRepositoryProvider.get());
  }

  public static MaintenanceListViewModel_Factory create(
      Provider<MaintenanceScheduleRepository> maintenanceScheduleRepositoryProvider) {
    return new MaintenanceListViewModel_Factory(maintenanceScheduleRepositoryProvider);
  }

  public static MaintenanceListViewModel newInstance(
      MaintenanceScheduleRepository maintenanceScheduleRepository) {
    return new MaintenanceListViewModel(maintenanceScheduleRepository);
  }
}
