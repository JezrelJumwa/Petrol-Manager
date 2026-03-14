package com.cartracker.app.presentation.screens.mileage;

import com.cartracker.app.data.repository.MileageLogRepository;
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
public final class MileageLogViewModel_Factory implements Factory<MileageLogViewModel> {
  private final Provider<MileageLogRepository> mileageLogRepositoryProvider;

  private final Provider<VehicleRepository> vehicleRepositoryProvider;

  public MileageLogViewModel_Factory(Provider<MileageLogRepository> mileageLogRepositoryProvider,
      Provider<VehicleRepository> vehicleRepositoryProvider) {
    this.mileageLogRepositoryProvider = mileageLogRepositoryProvider;
    this.vehicleRepositoryProvider = vehicleRepositoryProvider;
  }

  @Override
  public MileageLogViewModel get() {
    return newInstance(mileageLogRepositoryProvider.get(), vehicleRepositoryProvider.get());
  }

  public static MileageLogViewModel_Factory create(
      Provider<MileageLogRepository> mileageLogRepositoryProvider,
      Provider<VehicleRepository> vehicleRepositoryProvider) {
    return new MileageLogViewModel_Factory(mileageLogRepositoryProvider, vehicleRepositoryProvider);
  }

  public static MileageLogViewModel newInstance(MileageLogRepository mileageLogRepository,
      VehicleRepository vehicleRepository) {
    return new MileageLogViewModel(mileageLogRepository, vehicleRepository);
  }
}
