package com.cartracker.app.data.repository;

import com.cartracker.app.data.dao.MileageLogDao;
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
public final class MileageLogRepository_Factory implements Factory<MileageLogRepository> {
  private final Provider<MileageLogDao> mileageLogDaoProvider;

  public MileageLogRepository_Factory(Provider<MileageLogDao> mileageLogDaoProvider) {
    this.mileageLogDaoProvider = mileageLogDaoProvider;
  }

  @Override
  public MileageLogRepository get() {
    return newInstance(mileageLogDaoProvider.get());
  }

  public static MileageLogRepository_Factory create(Provider<MileageLogDao> mileageLogDaoProvider) {
    return new MileageLogRepository_Factory(mileageLogDaoProvider);
  }

  public static MileageLogRepository newInstance(MileageLogDao mileageLogDao) {
    return new MileageLogRepository(mileageLogDao);
  }
}
