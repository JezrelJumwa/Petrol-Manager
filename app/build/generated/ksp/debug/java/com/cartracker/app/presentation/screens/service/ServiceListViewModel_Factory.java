package com.cartracker.app.presentation.screens.service;

import com.cartracker.app.data.repository.ServiceRecordRepository;
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
public final class ServiceListViewModel_Factory implements Factory<ServiceListViewModel> {
  private final Provider<ServiceRecordRepository> serviceRecordRepositoryProvider;

  public ServiceListViewModel_Factory(
      Provider<ServiceRecordRepository> serviceRecordRepositoryProvider) {
    this.serviceRecordRepositoryProvider = serviceRecordRepositoryProvider;
  }

  @Override
  public ServiceListViewModel get() {
    return newInstance(serviceRecordRepositoryProvider.get());
  }

  public static ServiceListViewModel_Factory create(
      Provider<ServiceRecordRepository> serviceRecordRepositoryProvider) {
    return new ServiceListViewModel_Factory(serviceRecordRepositoryProvider);
  }

  public static ServiceListViewModel newInstance(ServiceRecordRepository serviceRecordRepository) {
    return new ServiceListViewModel(serviceRecordRepository);
  }
}
