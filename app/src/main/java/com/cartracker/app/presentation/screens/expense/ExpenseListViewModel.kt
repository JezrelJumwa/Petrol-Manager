package com.cartracker.app.presentation.screens.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.ExpenseEntity
import com.cartracker.app.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _expenses = MutableStateFlow<List<ExpenseEntity>>(emptyList())
    val expenses: StateFlow<List<ExpenseEntity>> = _expenses.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _totalExpenses = MutableStateFlow(0.0)
    val totalExpenses: StateFlow<Double> = _totalExpenses.asStateFlow()

    fun loadExpenses(vehicleId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            expenseRepository.getExpensesByVehicle(vehicleId).collect { expenses ->
                _expenses.value = expenses
                _isLoading.value = false
            }
            loadTotalExpenses(vehicleId)
        }
    }

    private fun loadTotalExpenses(vehicleId: Long) {
        viewModelScope.launch {
            val total = expenseRepository.getTotalExpenses(vehicleId)
            _totalExpenses.value = total
        }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            expenseRepository.deleteExpense(expense)
        }
    }
}
