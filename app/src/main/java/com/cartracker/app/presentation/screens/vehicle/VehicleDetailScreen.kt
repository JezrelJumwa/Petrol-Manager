package com.cartracker.app.presentation.screens.vehicle

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    onNavigateToServices: (Long) -> Unit,
    onNavigateToMaintenance: (Long) -> Unit,
    onNavigateToMileageLogs: (Long) -> Unit,
    onNavigateToParts: (Long) -> Unit,
    onNavigateToExpenses: (Long) -> Unit,
    viewModel: VehicleDetailViewModel = hiltViewModel()
) {
    val vehicle by viewModel.vehicle.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(vehicle?.let { "${it.year} ${it.make} ${it.model}" } ?: "Vehicle Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Vehicle profile card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = null,
                        modifier = Modifier.size(56.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Column {
                        if (vehicle != null) {
                            Text(
                                text = "${vehicle!!.year} ${vehicle!!.make} ${vehicle!!.model}",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "License: ${vehicle!!.licensePlate}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Mileage: ${String.format("%,d", vehicle!!.currentMileage)} mi",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            vehicle!!.vin?.let {
                                Text(
                                    text = "VIN: $it",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            vehicle!!.notes?.let {
                                if (it.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        } else {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    }
                }
            }

            HorizontalDivider()

            Text(
                text = "Manage",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            NavButton(
                label = "Service History",
                icon = { Icon(Icons.Default.History, contentDescription = null) },
                onClick = { onNavigateToServices(vehicleId) }
            )
            NavButton(
                label = "Maintenance Schedule",
                icon = { Icon(Icons.Default.Build, contentDescription = null) },
                onClick = { onNavigateToMaintenance(vehicleId) }
            )
            NavButton(
                label = "Mileage Logs",
                icon = { Icon(Icons.Default.Speed, contentDescription = null) },
                onClick = { onNavigateToMileageLogs(vehicleId) }
            )
            NavButton(
                label = "Parts Tracking",
                icon = { Icon(Icons.Default.Inventory, contentDescription = null) },
                onClick = { onNavigateToParts(vehicleId) }
            )
            NavButton(
                label = "Expenses",
                icon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
                onClick = { onNavigateToExpenses(vehicleId) }
            )
        }
    }
}

@Composable
private fun NavButton(
    label: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            icon()
            Text(label, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
