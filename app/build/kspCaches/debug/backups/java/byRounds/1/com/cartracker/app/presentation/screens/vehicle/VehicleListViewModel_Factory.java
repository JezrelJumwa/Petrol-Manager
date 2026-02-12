package com.cartracker.app.presentation.screens.vehicle;

import com.cartracker.app.data.repository.VehicleRepository;
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
public final class VehicleListViewModel_Factory implements Factory<VehicleListViewModel> {
  private final Provider<VehicleRepository> vehicleRepositoryProvider;

  public VehicleListViewModel_Factory(Provider<VehicleRepository> vehicleRepositoryProvider) {
    this.vehicleRepositoryProvider = vehicleRepositoryProvider;
  }

  @Override
  public VehicleListViewModel get() {
    return newInstance(vehicleRepositoryProvider.get());
  }

  public static VehicleListViewModel_Factory create(
      Provider<VehicleRepository> vehicleRepositoryProvider) {
    return new VehicleListViewModel_Factory(vehicleRepositoryProvider);
  }

  public static VehicleListViewModel newInstance(VehicleRepository vehicleRepository) {
    return new VehicleListViewModel(vehicleRepository);
  }
}
