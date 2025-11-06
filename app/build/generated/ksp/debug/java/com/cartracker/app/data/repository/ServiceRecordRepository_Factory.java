package com.cartracker.app.data.repository;

import com.cartracker.app.data.dao.ServiceRecordDao;
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
public final class ServiceRecordRepository_Factory implements Factory<ServiceRecordRepository> {
  private final Provider<ServiceRecordDao> serviceRecordDaoProvider;

  public ServiceRecordRepository_Factory(Provider<ServiceRecordDao> serviceRecordDaoProvider) {
    this.serviceRecordDaoProvider = serviceRecordDaoProvider;
  }

  @Override
  public ServiceRecordRepository get() {
    return newInstance(serviceRecordDaoProvider.get());
  }

  public static ServiceRecordRepository_Factory create(
      Provider<ServiceRecordDao> serviceRecordDaoProvider) {
    return new ServiceRecordRepository_Factory(serviceRecordDaoProvider);
  }

  public static ServiceRecordRepository newInstance(ServiceRecordDao serviceRecordDao) {
    return new ServiceRecordRepository(serviceRecordDao);
  }
}
