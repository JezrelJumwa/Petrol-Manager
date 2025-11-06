package com.cartracker.app.presentation.screens.vehicle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVehicleScreen(
    viewModel: AddVehicleViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            // Error will be shown in Snackbar
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Vehicle") },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.make,
                onValueChange = viewModel::onMakeChange,
                label = { Text("Make *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.error != null && uiState.make.isBlank()
            )

            OutlinedTextField(
                value = uiState.model,
                onValueChange = viewModel::onModelChange,
                label = { Text("Model *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.error != null && uiState.model.isBlank()
            )

            OutlinedTextField(
                value = uiState.year,
                onValueChange = viewModel::onYearChange,
                label = { Text("Year *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = uiState.error != null && uiState.year.isBlank()
            )

            OutlinedTextField(
                value = uiState.licensePlate,
                onValueChange = viewModel::onLicensePlateChange,
                label = { Text("License Plate *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.error != null && uiState.licensePlate.isBlank()
            )

            OutlinedTextField(
                value = uiState.vin,
                onValueChange = viewModel::onVinChange,
                label = { Text("VIN (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.currentMileage,
                onValueChange = viewModel::onMileageChange,
                label = { Text("Current Mileage *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = uiState.error != null && uiState.currentMileage.isBlank()
            )

            OutlinedTextField(
                value = uiState.notes,
                onValueChange = viewModel::onNotesChange,
                label = { Text("Notes (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )

            if (uiState.error != null) {
                Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = { viewModel.saveVehicle(onNavigateBack) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSaving
            ) {
                if (uiState.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Save Vehicle")
                }
            }
        }
    }
}
