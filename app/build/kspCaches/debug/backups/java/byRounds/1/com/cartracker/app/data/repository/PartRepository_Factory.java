package com.cartracker.app.data.repository;

import com.cartracker.app.data.dao.PartDao;
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
public final class PartRepository_Factory implements Factory<PartRepository> {
  private final Provider<PartDao> partDaoProvider;

  public PartRepository_Factory(Provider<PartDao> partDaoProvider) {
    this.partDaoProvider = partDaoProvider;
  }

  @Override
  public PartRepository get() {
    return newInstance(partDaoProvider.get());
  }

  public static PartRepository_Factory create(Provider<PartDao> partDaoProvider) {
    return new PartRepository_Factory(partDaoProvider);
  }

  public static PartRepository newInstance(PartDao partDao) {
    return new PartRepository(partDao);
  }
}
