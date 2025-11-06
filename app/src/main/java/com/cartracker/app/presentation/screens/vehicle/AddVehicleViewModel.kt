package com.cartracker.app.presentation.screens.vehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.VehicleEntity
import com.cartracker.app.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddVehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddVehicleUiState())
    val uiState: StateFlow<AddVehicleUiState> = _uiState.asStateFlow()

    fun onMakeChange(make: String) {
        _uiState.value = _uiState.value.copy(make = make)
    }

    fun onModelChange(model: String) {
        _uiState.value = _uiState.value.copy(model = model)
    }

    fun onYearChange(year: String) {
        _uiState.value = _uiState.value.copy(year = year)
    }

    fun onLicensePlateChange(plate: String) {
        _uiState.value = _uiState.value.copy(licensePlate = plate)
    }

    fun onVinChange(vin: String) {
        _uiState.value = _uiState.value.copy(vin = vin)
    }

    fun onMileageChange(mileage: String) {
        _uiState.value = _uiState.value.copy(currentMileage = mileage)
    }

    fun onNotesChange(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun saveVehicle(onSuccess: () -> Unit) {
        val state = _uiState.value
        
        if (state.make.isBlank() || state.model.isBlank() || state.year.isBlank() ||
            state.licensePlate.isBlank() || state.currentMileage.isBlank()) {
            _uiState.value = state.copy(error = "Please fill in all required fields")
            return
        }

        val yearInt = state.year.toIntOrNull()
        if (yearInt == null || yearInt < 1900 || yearInt > 2100) {
            _uiState.value = state.copy(error = "Please enter a valid year")
            return
        }

        val mileageInt = state.currentMileage.toIntOrNull()
        if (mileageInt == null || mileageInt < 0) {
            _uiState.value = state.copy(error = "Please enter a valid mileage")
            return
        }

        viewModelScope.launch {
            _uiState.value = state.copy(isSaving = true, error = null)
            try {
                val vehicle = VehicleEntity(
                    make = state.make.trim(),
                    model = state.model.trim(),
                    year = yearInt,
                    licensePlate = state.licensePlate.trim(),
                    vin = state.vin.trim().takeIf { it.isNotBlank() },
                    currentMileage = mileageInt,
                    notes = state.notes.trim().takeIf { it.isNotBlank() }
                )
                vehicleRepository.insertVehicle(vehicle)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = state.copy(isSaving = false, error = "Failed to save vehicle: ${e.message}")
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class AddVehicleUiState(
    val make: String = "",
    val model: String = "",
    val year: String = "",
    val licensePlate: String = "",
    val vin: String = "",
    val currentMileage: String = "",
    val notes: String = "",
    val isSaving: Boolean = false,
    val error: String? = null
)
