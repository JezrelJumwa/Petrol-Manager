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
public final class AddVehicleViewModel_Factory implements Factory<AddVehicleViewModel> {
  private final Provider<VehicleRepository> vehicleRepositoryProvider;

  public AddVehicleViewModel_Factory(Provider<VehicleRepository> vehicleRepositoryProvider) {
    this.vehicleRepositoryProvider = vehicleRepositoryProvider;
  }

  @Override
  public AddVehicleViewModel get() {
    return newInstance(vehicleRepositoryProvider.get());
  }

  public static AddVehicleViewModel_Factory create(
      Provider<VehicleRepository> vehicleRepositoryProvider) {
    return new AddVehicleViewModel_Factory(vehicleRepositoryProvider);
  }

  public static AddVehicleViewModel newInstance(VehicleRepository vehicleRepository) {
    return new AddVehicleViewModel(vehicleRepository);
  }
}
