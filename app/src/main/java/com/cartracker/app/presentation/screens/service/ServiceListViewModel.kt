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
class ServiceListViewModel @Inject constructor(
    private val serviceRecordRepository: ServiceRecordRepository
) : ViewModel() {

    private val _serviceRecords = MutableStateFlow<List<ServiceRecordEntity>>(emptyList())
    val serviceRecords: StateFlow<List<ServiceRecordEntity>> = _serviceRecords.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _totalCost = MutableStateFlow(0.0)
    val totalCost: StateFlow<Double> = _totalCost.asStateFlow()

    fun loadServiceRecords(vehicleId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            serviceRecordRepository.getServiceRecordsByVehicle(vehicleId).collect { records ->
                _serviceRecords.value = records
                _isLoading.value = false
            }
            loadTotalCost(vehicleId)
        }
    }

    private fun loadTotalCost(vehicleId: Long) {
        viewModelScope.launch {
            val cost = serviceRecordRepository.getTotalServiceCost(vehicleId)
            _totalCost.value = cost
        }
    }

    fun deleteServiceRecord(record: ServiceRecordEntity) {
        viewModelScope.launch {
            serviceRecordRepository.deleteServiceRecord(record)
        }
    }
}
