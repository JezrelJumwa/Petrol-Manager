package com.cartracker.app.presentation.screens.parts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cartracker.app.data.model.PartEntity
import com.cartracker.app.data.repository.PartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartsListViewModel @Inject constructor(
    private val partRepository: PartRepository
) : ViewModel() {

    private val _parts = MutableStateFlow<List<PartEntity>>(emptyList())
    val parts: StateFlow<List<PartEntity>> = _parts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadParts(vehicleId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            partRepository.getPartsByVehicle(vehicleId).collect { parts ->
                _parts.value = parts
                _isLoading.value = false
            }
        }
    }

    fun updatePart(part: PartEntity) {
        viewModelScope.launch {
            partRepository.updatePart(part)
        }
    }

    fun deletePart(part: PartEntity) {
        viewModelScope.launch {
            partRepository.deletePart(part)
        }
    }
}
