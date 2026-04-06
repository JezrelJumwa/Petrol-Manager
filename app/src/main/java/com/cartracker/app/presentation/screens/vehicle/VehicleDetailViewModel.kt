package com.cartracker.app.presentation.screens.vehicle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.VehicleEntity
import com.cartracker.app.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VehicleDetailViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val vehicleId: Long = checkNotNull(savedStateHandle["vehicleId"])

    val vehicle: StateFlow<VehicleEntity?> = vehicleRepository.getVehicleById(vehicleId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}
