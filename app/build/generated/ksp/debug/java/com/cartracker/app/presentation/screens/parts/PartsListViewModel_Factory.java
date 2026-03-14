package com.cartracker.app.presentation.screens.parts;

import com.cartracker.app.data.repository.PartRepository;
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
public final class PartsListViewModel_Factory implements Factory<PartsListViewModel> {
  private final Provider<PartRepository> partRepositoryProvider;

  public PartsListViewModel_Factory(Provider<PartRepository> partRepositoryProvider) {
    this.partRepositoryProvider = partRepositoryProvider;
  }

  @Override
  public PartsListViewModel get() {
    return newInstance(partRepositoryProvider.get());
  }

  public static PartsListViewModel_Factory create(Provider<PartRepository> partRepositoryProvider) {
    return new PartsListViewModel_Factory(partRepositoryProvider);
  }

  public static PartsListViewModel newInstance(PartRepository partRepository) {
    return new PartsListViewModel(partRepository);
  }
}
