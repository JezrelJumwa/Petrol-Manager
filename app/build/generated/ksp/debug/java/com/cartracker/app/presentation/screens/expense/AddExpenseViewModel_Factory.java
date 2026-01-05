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
    "KotlinInternalInJava",
    "cast"
})
public final class AddExpenseViewModel_Factory implements Factory<AddExpenseViewModel> {
  private final Provider<ExpenseRepository> expenseRepositoryProvider;

  public AddExpenseViewModel_Factory(Provider<ExpenseRepository> expenseRepositoryProvider) {
    this.expenseRepositoryProvider = expenseRepositoryProvider;
  }

  @Override
  public AddExpenseViewModel get() {
    return newInstance(expenseRepositoryProvider.get());
  }

  public static AddExpenseViewModel_Factory create(
      Provider<ExpenseRepository> expenseRepositoryProvider) {
    return new AddExpenseViewModel_Factory(expenseRepositoryProvider);
  }

  public static AddExpenseViewModel newInstance(ExpenseRepository expenseRepository) {
    return new AddExpenseViewModel(expenseRepository);
  }
}
