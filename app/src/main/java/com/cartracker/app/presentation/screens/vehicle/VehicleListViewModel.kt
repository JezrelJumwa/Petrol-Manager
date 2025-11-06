package com.cartracker.app.presentation.screens.vehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.VehicleEntity
import com.cartracker.app.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    val uiState: StateFlow<VehicleListUiState> = vehicleRepository.getAllActiveVehicles()
        .map { vehicles ->
            if (vehicles.isEmpty()) {
                VehicleListUiState.Empty
            } else {
                VehicleListUiState.Success(vehicles)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = VehicleListUiState.Loading
        )
}

sealed interface VehicleListUiState {
    object Loading : VehicleListUiState
    object Empty : VehicleListUiState
    data class Success(val vehicles: List<VehicleEntity>) : VehicleListUiState
}
