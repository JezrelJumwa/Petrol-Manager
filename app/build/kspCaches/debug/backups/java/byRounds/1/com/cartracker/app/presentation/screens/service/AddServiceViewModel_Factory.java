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
    "KotlinInternalInJava",
    "cast"
})
public final class AddServiceViewModel_Factory implements Factory<AddServiceViewModel> {
  private final Provider<ServiceRecordRepository> serviceRecordRepositoryProvider;

  public AddServiceViewModel_Factory(
      Provider<ServiceRecordRepository> serviceRecordRepositoryProvider) {
    this.serviceRecordRepositoryProvider = serviceRecordRepositoryProvider;
  }

  @Override
  public AddServiceViewModel get() {
    return newInstance(serviceRecordRepositoryProvider.get());
  }

  public static AddServiceViewModel_Factory create(
      Provider<ServiceRecordRepository> serviceRecordRepositoryProvider) {
    return new AddServiceViewModel_Factory(serviceRecordRepositoryProvider);
  }

  public static AddServiceViewModel newInstance(ServiceRecordRepository serviceRecordRepository) {
    return new AddServiceViewModel(serviceRecordRepository);
  }
}
