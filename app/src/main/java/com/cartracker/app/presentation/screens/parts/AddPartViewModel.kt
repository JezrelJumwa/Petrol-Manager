package com.cartracker.app.presentation.screens.parts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.PartEntity
import com.cartracker.app.data.model.ServiceRecordEntity
import com.cartracker.app.data.repository.PartRepository
import com.cartracker.app.data.repository.ServiceRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddPartUiState(
    val serviceRecords: List<ServiceRecordEntity> = emptyList(),
    val selectedServiceRecord: ServiceRecordEntity? = null,
    val partName: String = "",
    val partNumber: String = "",
    val brand: String = "",
    val quantity: String = "1",
    val cost: String = "",
    val currency: String = "KSH",
    val warranty: String = "",
    val isSaving: Boolean = false,
    val serviceRecordError: String? = null,
    val partNameError: String? = null,
    val costError: String? = null
)

@HiltViewModel
class AddPartViewModel @Inject constructor(
    private val partRepository: PartRepository,
    private val serviceRecordRepository: ServiceRecordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddPartUiState())
    val uiState: StateFlow<AddPartUiState> = _uiState.asStateFlow()

    fun loadServiceRecords(vehicleId: Long) {
        viewModelScope.launch {
            serviceRecordRepository.getServiceRecordsByVehicle(vehicleId).collect { records ->
                _uiState.value = _uiState.value.copy(serviceRecords = records)
            }
        }
    }

    fun onServiceRecordSelect(record: ServiceRecordEntity) {
        _uiState.value = _uiState.value.copy(
            selectedServiceRecord = record,
            serviceRecordError = null
        )
    }

    fun onPartNameChange(value: String) {
        _uiState.value = _uiState.value.copy(partName = value, partNameError = null)
    }

    fun onPartNumberChange(value: String) {
        _uiState.value = _uiState.value.copy(partNumber = value)
    }

    fun onBrandChange(value: String) {
        _uiState.value = _uiState.value.copy(brand = value)
    }

    fun onQuantityChange(value: String) {
        _uiState.value = _uiState.value.copy(quantity = value)
    }

    fun onCostChange(value: String) {
        _uiState.value = _uiState.value.copy(cost = value, costError = null)
    }

    fun onCurrencyChange(value: String) {
        _uiState.value = _uiState.value.copy(currency = value)
    }

    fun onWarrantyChange(value: String) {
        _uiState.value = _uiState.value.copy(warranty = value)
    }

    fun savePart(onSuccess: () -> Unit) {
        val state = _uiState.value
        var hasError = false

        if (state.selectedServiceRecord == null) {
            _uiState.value = _uiState.value.copy(serviceRecordError = "Please select a service record")
            hasError = true
        }
        if (state.partName.isBlank()) {
            _uiState.value = _uiState.value.copy(partNameError = "Part name is required")
            hasError = true
        }
        val costDouble = state.cost.toDoubleOrNull()
        if (costDouble == null || costDouble < 0) {
            _uiState.value = _uiState.value.copy(costError = "Enter a valid cost")
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSaving = true)
            partRepository.insertPart(
                PartEntity(
                    serviceRecordId = state.selectedServiceRecord!!.id,
                    partName = state.partName.trim(),
                    partNumber = state.partNumber.trim().takeIf { it.isNotBlank() },
                    brand = state.brand.trim().takeIf { it.isNotBlank() },
                    quantity = state.quantity.toIntOrNull()?.coerceAtLeast(1) ?: 1,
                    cost = costDouble!!,
                    currency = state.currency,
                    warranty = state.warranty.trim().takeIf { it.isNotBlank() }
                )
            )
            _uiState.value = _uiState.value.copy(isSaving = false)
            onSuccess()
        }
    }
}
