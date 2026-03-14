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
public final class AddMaintenanceViewModel_Factory implements Factory<AddMaintenanceViewModel> {
  private final Provider<MaintenanceScheduleRepository> maintenanceScheduleRepositoryProvider;

  public AddMaintenanceViewModel_Factory(
      Provider<MaintenanceScheduleRepository> maintenanceScheduleRepositoryProvider) {
    this.maintenanceScheduleRepositoryProvider = maintenanceScheduleRepositoryProvider;
  }

  @Override
  public AddMaintenanceViewModel get() {
    return newInstance(maintenanceScheduleRepositoryProvider.get());
  }

  public static AddMaintenanceViewModel_Factory create(
      Provider<MaintenanceScheduleRepository> maintenanceScheduleRepositoryProvider) {
    return new AddMaintenanceViewModel_Factory(maintenanceScheduleRepositoryProvider);
  }

  public static AddMaintenanceViewModel newInstance(
      MaintenanceScheduleRepository maintenanceScheduleRepository) {
    return new AddMaintenanceViewModel(maintenanceScheduleRepository);
  }
}
