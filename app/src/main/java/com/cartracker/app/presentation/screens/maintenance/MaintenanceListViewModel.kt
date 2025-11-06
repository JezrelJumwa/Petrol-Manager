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
class MaintenanceListViewModel @Inject constructor(
    private val maintenanceScheduleRepository: MaintenanceScheduleRepository
) : ViewModel() {

    private val _schedules = MutableStateFlow<List<MaintenanceScheduleEntity>>(emptyList())
    val schedules: StateFlow<List<MaintenanceScheduleEntity>> = _schedules.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadSchedules(vehicleId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            maintenanceScheduleRepository.getActiveSchedulesByVehicle(vehicleId).collect { schedules ->
                _schedules.value = schedules
                _isLoading.value = false
            }
        }
    }

    fun deleteSchedule(schedule: MaintenanceScheduleEntity) {
        viewModelScope.launch {
            maintenanceScheduleRepository.deleteSchedule(schedule)
        }
    }
}
