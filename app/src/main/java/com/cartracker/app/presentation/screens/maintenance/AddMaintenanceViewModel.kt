package com.cartracker.app.presentation.screens.maintenance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.MaintenanceScheduleEntity
import com.cartracker.app.data.repository.MaintenanceScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMaintenanceViewModel @Inject constructor(
    private val maintenanceScheduleRepository: MaintenanceScheduleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddMaintenanceUiState())
    val uiState: StateFlow<AddMaintenanceUiState> = _uiState.asStateFlow()

    val maintenanceTypes = listOf(
        "Oil Change",
        "Tire Rotation",
        "Brake Inspection",
        "Battery Check",
        "Engine Air Filter",
        "Cabin Air Filter",
        "Coolant Service",
        "Transmission Service",
        "Other"
    )

    fun onMaintenanceTypeChange(type: String) {
        _uiState.value = _uiState.value.copy(maintenanceType = type, maintenanceTypeError = null)
    }

    fun onIntervalMilesChange(value: String) {
        _uiState.value = _uiState.value.copy(intervalMiles = value, intervalError = null)
    }

    fun onIntervalMonthsChange(value: String) {
        _uiState.value = _uiState.value.copy(intervalMonths = value, intervalError = null)
    }

    fun onNextDueMileageChange(value: String) {
        _uiState.value = _uiState.value.copy(nextDueMileage = value)
    }

    fun onNextDueDateChange(dateMillis: Long?) {
        _uiState.value = _uiState.value.copy(nextDueDateMillis = dateMillis)
    }

    fun onReminderEnabledChange(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(reminderEnabled = enabled)
    }

    fun saveSchedule(vehicleId: Long, onSuccess: () -> Unit) {
        val state = _uiState.value
        var hasError = false

        if (state.maintenanceType.isBlank()) {
            _uiState.value = state.copy(maintenanceTypeError = "Maintenance type is required")
            hasError = true
        }

        val intervalMilesInt = state.intervalMiles.toIntOrNull()?.takeIf { it > 0 }
        val intervalMonthsInt = state.intervalMonths.toIntOrNull()?.takeIf { it > 0 }

        if (intervalMilesInt == null && intervalMonthsInt == null) {
            _uiState.value = _uiState.value.copy(intervalError = "Provide miles or months interval")
            hasError = true
        }

        if (hasError) return

        val nextDueMileageInt = state.nextDueMileage.toIntOrNull()?.takeIf { it > 0 }

        viewModelScope.launch {
            _uiState.value = state.copy(isSaving = true, error = null)
            try {
                val schedule = MaintenanceScheduleEntity(
                    vehicleId = vehicleId,
                    maintenanceType = state.maintenanceType.trim(),
                    intervalMiles = intervalMilesInt,
                    intervalMonths = intervalMonthsInt,
                    nextDueDate = state.nextDueDateMillis,
                    nextDueMileage = nextDueMileageInt,
                    reminderEnabled = state.reminderEnabled
                )
                maintenanceScheduleRepository.insertSchedule(schedule)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = state.copy(
                    isSaving = false,
                    error = "Failed to save schedule: ${e.message}"
                )
            }
        }
    }
}

data class AddMaintenanceUiState(
    val maintenanceType: String = "",
    val intervalMiles: String = "",
    val intervalMonths: String = "",
    val nextDueDateMillis: Long? = null,
    val nextDueMileage: String = "",
    val reminderEnabled: Boolean = true,
    val isSaving: Boolean = false,
    val error: String? = null,
    val maintenanceTypeError: String? = null,
    val intervalError: String? = null
)
