package com.cartracker.app.presentation.screens.mileage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.MileageLogEntity
import com.cartracker.app.data.repository.MileageLogRepository
import com.cartracker.app.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MileageLogViewModel @Inject constructor(
    private val mileageLogRepository: MileageLogRepository,
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _logs = MutableStateFlow<List<MileageLogEntity>>(emptyList())
    val logs: StateFlow<List<MileageLogEntity>> = _logs.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _newMileage = MutableStateFlow("")
    val newMileage: StateFlow<String> = _newMileage.asStateFlow()

    private val _newNotes = MutableStateFlow("")
    val newNotes: StateFlow<String> = _newNotes.asStateFlow()

    private val _inputError = MutableStateFlow<String?>(null)
    val inputError: StateFlow<String?> = _inputError.asStateFlow()

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    fun loadLogs(vehicleId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            mileageLogRepository.getMileageLogsByVehicle(vehicleId).collect { logs ->
                _logs.value = logs
                _isLoading.value = false
            }
        }
    }

    fun onMileageInputChange(value: String) {
        _newMileage.value = value
        _inputError.value = null
    }

    fun onNotesInputChange(value: String) {
        _newNotes.value = value
    }

    fun addMileageLog(vehicleId: Long) {
        val mileageValue = _newMileage.value.toIntOrNull()
        if (mileageValue == null || mileageValue < 0) {
            _inputError.value = "Please enter a valid mileage"
            return
        }

        viewModelScope.launch {
            _isSaving.value = true
            try {
                mileageLogRepository.insertMileageLog(
                    MileageLogEntity(
                        vehicleId = vehicleId,
                        mileage = mileageValue,
                        notes = _newNotes.value.trim().takeIf { it.isNotBlank() }
                    )
                )
                vehicleRepository.updateMileage(vehicleId, mileageValue)
                _newMileage.value = ""
                _newNotes.value = ""
            } finally {
                _isSaving.value = false
            }
        }
    }

    fun deleteLog(log: MileageLogEntity) {
        viewModelScope.launch {
            mileageLogRepository.deleteMileageLog(log)
        }
    }

    fun updateLog(log: MileageLogEntity, mileage: Int, notes: String) {
        viewModelScope.launch {
            val updatedLog = log.copy(
                mileage = mileage,
                notes = notes.trim().takeIf { it.isNotBlank() }
            )
            mileageLogRepository.updateMileageLog(updatedLog)
            vehicleRepository.updateMileage(log.vehicleId, mileage)
        }
    }
}
