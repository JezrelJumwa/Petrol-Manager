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
class AddExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddExpenseUiState())
    val uiState: StateFlow<AddExpenseUiState> = _uiState.asStateFlow()

    val expenseCategories = listOf(
        "Fuel",
        "Insurance",
        "Registration",
        "Parking",
        "Tolls",
        "Car Wash",
        "Maintenance",
        "Repairs",
        "Other"
    )

    fun onCategoryChange(category: String) {
        _uiState.value = _uiState.value.copy(category = category, categoryError = null)
    }

    fun onExpenseDateChange(dateMillis: Long) {
        _uiState.value = _uiState.value.copy(expenseDateMillis = dateMillis, expenseDateError = null)
    }

    fun onAmountChange(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount, amountError = null)
    }

    fun onVendorChange(vendor: String) {
        _uiState.value = _uiState.value.copy(vendor = vendor)
    }

    fun onMileageChange(mileage: String) {
        _uiState.value = _uiState.value.copy(mileage = mileage)
    }

    fun onNotesChange(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun saveExpense(vehicleId: Long, onSuccess: () -> Unit) {
        val state = _uiState.value
        var hasError = false

        // Validate required fields
        if (state.category.isBlank()) {
            _uiState.value = state.copy(categoryError = "Category is required")
            hasError = true
        }

        if (state.expenseDateMillis == null) {
            _uiState.value = _uiState.value.copy(expenseDateError = "Expense date is required")
            hasError = true
        }

        val amountDouble = state.amount.toDoubleOrNull()
        if (amountDouble == null || amountDouble < 0) {
            _uiState.value = _uiState.value.copy(amountError = "Please enter a valid amount")
            hasError = true
        }

        if (hasError) return

        val mileageInt = state.mileage.toIntOrNull()?.takeIf { it > 0 }

        viewModelScope.launch {
            _uiState.value = state.copy(isSaving = true, error = null)
            try {
                val expense = ExpenseEntity(
                    vehicleId = vehicleId,
                    category = state.category.trim(),
                    expenseDate = state.expenseDateMillis!!,
                    amount = amountDouble!!,
                    vendor = state.vendor.trim().takeIf { it.isNotBlank() },
                    mileageAtExpense = mileageInt,
                    notes = state.notes.trim().takeIf { it.isNotBlank() }
                )
                expenseRepository.insertExpense(expense)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = state.copy(
                    isSaving = false,
                    error = "Failed to save expense: ${e.message}"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class AddExpenseUiState(
    val category: String = "",
    val expenseDateMillis: Long? = null,
    val amount: String = "",
    val vendor: String = "",
    val mileage: String = "",
    val notes: String = "",
    val isSaving: Boolean = false,
    val error: String? = null,
    val categoryError: String? = null,
    val expenseDateError: String? = null,
    val amountError: String? = null
)
