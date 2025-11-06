package com.cartracker.app.presentation.screens.expense;

import com.cartracker.app.data.repository.ExpenseRepository;
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
public final class ExpenseListViewModel_Factory implements Factory<ExpenseListViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  public ExpenseListViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
  }

  @Override
  public ExpenseListViewModel get() {
    return newInstance(expenseRepositoryProvider.get());
  }

  public static ExpenseListViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    return new ExpenseListViewModel_Factory(expenseRepositoryProvider);
  }

  public static ExpenseListViewModel newInstance(ExpenseRepository expenseRepository) {
    return new ExpenseListViewModel(expenseRepository);
  }
}
