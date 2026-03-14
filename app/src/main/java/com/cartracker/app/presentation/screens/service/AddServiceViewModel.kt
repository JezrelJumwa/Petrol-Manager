package com.cartracker.app.presentation.screens.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.ServiceRecordEntity
import com.cartracker.app.data.repository.ServiceRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddServiceViewModel @Inject constructor(
    private val serviceRecordRepository: ServiceRecordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddServiceUiState())
    val uiState: StateFlow<AddServiceUiState> = _uiState.asStateFlow()

    val serviceTypes = listOf(
        "Oil Change",
        "Brake Service",
        "Tire Rotation",
        "Inspection",
        "Repair",
        "Filter Replacement",
        "Transmission Service",
        "Coolant Flush",
        "Other"
    )

    fun onServiceTypeChange(type: String) {
        _uiState.value = _uiState.value.copy(serviceType = type, serviceTypeError = null)
    }

    fun onServiceDateChange(dateMillis: Long) {
        _uiState.value = _uiState.value.copy(serviceDateMillis = dateMillis, serviceDateError = null)
    }

    fun onMileageChange(mileage: String) {
        _uiState.value = _uiState.value.copy(mileage = mileage, mileageError = null)
    }

    fun onCostChange(cost: String) {
        _uiState.value = _uiState.value.copy(cost = cost, costError = null)
    }

    fun onShopChange(shop: String) {
        _uiState.value = _uiState.value.copy(servicedBy = shop)
    }

    fun onNotesChange(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun onNextDueDateChange(dateMillis: Long?) {
        _uiState.value = _uiState.value.copy(nextDueDateMillis = dateMillis)
    }

    fun onNextDueMileageChange(mileage: String) {
        _uiState.value = _uiState.value.copy(nextDueMileage = mileage)
    }

    fun onCurrencyChange(currency: String) {
        _uiState.value = _uiState.value.copy(currency = currency)
    }


    fun saveService(vehicleId: Long, onSuccess: () -> Unit) {
        val state = _uiState.value
        var hasError = false

        // Validate required fields
        if (state.serviceType.isBlank()) {
            _uiState.value = state.copy(serviceTypeError = "Service type is required")
            hasError = true
        }

        if (state.serviceDateMillis == null) {
            _uiState.value = _uiState.value.copy(serviceDateError = "Service date is required")
            hasError = true
        }

        val mileageInt = state.mileage.toIntOrNull()
        if (mileageInt == null || mileageInt < 0) {
            _uiState.value = _uiState.value.copy(mileageError = "Please enter a valid mileage")
            hasError = true
        }

        val costDouble = state.cost.toDoubleOrNull()
        if (costDouble == null || costDouble < 0) {
            _uiState.value = _uiState.value.copy(costError = "Please enter a valid cost")
            hasError = true
        }

        if (hasError) return

        val nextDueMileageInt = state.nextDueMileage.toIntOrNull()?.takeIf { it > 0 }

        viewModelScope.launch {
            _uiState.value = state.copy(isSaving = true, error = null)
            try {
                val serviceRecord = ServiceRecordEntity(
                    vehicleId = vehicleId,
                    serviceType = state.serviceType.trim(),
                    serviceDate = state.serviceDateMillis!!,
                    mileageAtService = mileageInt!!,
                    cost = costDouble!!,
                    servicedBy = state.servicedBy.trim().takeIf { it.isNotBlank() },
                    notes = state.notes.trim().takeIf { it.isNotBlank() },
                    nextDueDate = state.nextDueDateMillis,
                    nextDueMileage = nextDueMileageInt?.toLong(),
                    currency = state.currency
                )
                serviceRecordRepository.insertServiceRecord(serviceRecord)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = state.copy(
                    isSaving = false,
                    error = "Failed to save service: ${e.message}"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class AddServiceUiState(
    val serviceType: String = "",
    val serviceDateMillis: Long? = null,
    val mileage: String = "",
    val cost: String = "",
    val servicedBy: String = "",
    val notes: String = "",
    val nextDueDateMillis: Long? = null,
    val nextDueMileage: String = "",
    val currency: String = "KSH",
    val isSaving: Boolean = false,
    val error: String? = null,
    val serviceTypeError: String? = null,
    val serviceDateError: String? = null,
    val mileageError: String? = null,
    val costError: String? = null
)