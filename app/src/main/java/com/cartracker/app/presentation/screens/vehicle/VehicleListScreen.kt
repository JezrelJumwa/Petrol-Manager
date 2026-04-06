package com.cartracker.app.presentation.screens.vehicle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cartracker.app.data.model.VehicleEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleListScreen(
    viewModel: VehicleListViewModel = hiltViewModel(),
    onNavigateToAddVehicle: () -> Unit,
    onNavigateToVehicleDetail: (Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var editingVehicle by remember { mutableStateOf<VehicleEntity?>(null) }
    var deletingVehicle by remember { mutableStateOf<VehicleEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Vehicles") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAddVehicle) {
                Icon(Icons.Default.Add, contentDescription = "Add Vehicle")
            }
        }
    ) { padding ->
        when (val state = uiState) {
            is VehicleListUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is VehicleListUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No vehicles added yet",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tap + to add your first vehicle",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            is VehicleListUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.vehicles) { vehicle ->
                        VehicleCard(
                            vehicle = vehicle,
                            onClick = { onNavigateToVehicleDetail(vehicle.id) },
                            onEdit = { editingVehicle = vehicle },
                            onDelete = { deletingVehicle = vehicle }
                        )
                    }
                }
            }
        }
    }

    editingVehicle?.let { vehicle ->
        EditVehicleDialog(
            vehicle = vehicle,
            onDismiss = { editingVehicle = null },
            onSave = { updated ->
                viewModel.updateVehicle(updated)
                editingVehicle = null
            }
        )
    }

    deletingVehicle?.let { vehicle ->
        AlertDialog(
            onDismissRequest = { deletingVehicle = null },
            title = { Text("Delete Vehicle") },
            text = { Text("Delete ${vehicle.year} ${vehicle.make} ${vehicle.model}? All associated records will also be deleted.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteVehicle(vehicle)
                        deletingVehicle = null
                    }
                ) { Text("Delete", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = {
                TextButton(onClick = { deletingVehicle = null }) { Text("Cancel") }
            }
        )
    }
}

@Composable
fun VehicleCard(
    vehicle: VehicleEntity,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DirectionsCar,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${vehicle.year} ${vehicle.make} ${vehicle.model}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "License: ${vehicle.licensePlate}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Mileage: ${String.format("%,d", vehicle.currentMileage)} mi",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit vehicle")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete vehicle")
            }
        }
    }
}

@Composable
private fun EditVehicleDialog(
    vehicle: VehicleEntity,
    onDismiss: () -> Unit,
    onSave: (VehicleEntity) -> Unit
) {
    var make by remember(vehicle.id) { mutableStateOf(vehicle.make) }
    var model by remember(vehicle.id) { mutableStateOf(vehicle.model) }
    var year by remember(vehicle.id) { mutableStateOf(vehicle.year.toString()) }
    var licensePlate by remember(vehicle.id) { mutableStateOf(vehicle.licensePlate) }
    var mileage by remember(vehicle.id) { mutableStateOf(vehicle.currentMileage.toString()) }
    var notes by remember(vehicle.id) { mutableStateOf(vehicle.notes.orEmpty()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Vehicle") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = make, onValueChange = { make = it }, label = { Text("Make") }, singleLine = true)
                OutlinedTextField(value = model, onValueChange = { model = it }, label = { Text("Model") }, singleLine = true)
                OutlinedTextField(value = year, onValueChange = { year = it }, label = { Text("Year") }, singleLine = true)
                OutlinedTextField(value = licensePlate, onValueChange = { licensePlate = it }, label = { Text("License") }, singleLine = true)
                OutlinedTextField(value = mileage, onValueChange = { mileage = it }, label = { Text("Mileage") }, singleLine = true)
                OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes") }, minLines = 2)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val yearInt = year.toIntOrNull() ?: vehicle.year
                val mileageInt = mileage.toIntOrNull() ?: vehicle.currentMileage
                onSave(
                    vehicle.copy(
                        make = make.trim().ifBlank { vehicle.make },
                        model = model.trim().ifBlank { vehicle.model },
                        year = yearInt,
                        licensePlate = licensePlate.trim().ifBlank { vehicle.licensePlate },
                        currentMileage = mileageInt,
                        notes = notes.trim().takeIf { it.isNotBlank() },
                        updatedAt = System.currentTimeMillis()
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
