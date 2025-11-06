package com.cartracker.app.presentation.screens.vehicle

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    onNavigateToServices: (Long) -> Unit,
    onNavigateToMaintenance: (Long) -> Unit,
    onNavigateToExpenses: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vehicle Details") },
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
            Text(
                text = "Vehicle ID: $vehicleId",
                style = MaterialTheme.typography.titleLarge
            )
            
            Button(
                onClick = { onNavigateToServices(vehicleId) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Service History")
            }
            
            Button(
                onClick = { onNavigateToMaintenance(vehicleId) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Maintenance Schedule")
            }
            
            Button(
                onClick = { onNavigateToExpenses(vehicleId) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Expenses")
            }
        }
    }
}
