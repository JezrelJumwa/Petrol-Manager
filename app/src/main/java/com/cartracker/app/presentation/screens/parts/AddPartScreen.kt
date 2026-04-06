package com.cartracker.app.presentation.screens.parts

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
import com.cartracker.app.presentation.components.CurrencySelector
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPartScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    viewModel: AddPartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showServicePicker by remember { mutableStateOf(false) }
    val dateFormat = remember { SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()) }

    LaunchedEffect(vehicleId) {
        viewModel.loadServiceRecords(vehicleId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Part") },
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
            // Service Record selector
            ExposedDropdownMenuBox(
                expanded = showServicePicker,
                onExpandedChange = { showServicePicker = !showServicePicker }
            ) {
                OutlinedTextField(
                    value = uiState.selectedServiceRecord?.let {
                        "${it.serviceType} – ${dateFormat.format(Date(it.serviceDate))}"
                    } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Service Record *") },
                    placeholder = { Text("Select a service record") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showServicePicker) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    isError = uiState.serviceRecordError != null,
                    supportingText = uiState.serviceRecordError?.let { { Text(it) } }
                )
                ExposedDropdownMenu(
                    expanded = showServicePicker,
                    onDismissRequest = { showServicePicker = false }
                ) {
                    if (uiState.serviceRecords.isEmpty()) {
                        DropdownMenuItem(
                            text = { Text("No service records available") },
                            onClick = { showServicePicker = false },
                            enabled = false
                        )
                    } else {
                        uiState.serviceRecords.forEach { record ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(record.serviceType, style = MaterialTheme.typography.bodyMedium)
                                        Text(
                                            dateFormat.format(Date(record.serviceDate)),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                },
                                onClick = {
                                    viewModel.onServiceRecordSelect(record)
                                    showServicePicker = false
                                }
                            )
                        }
                    }
                }
            }

            OutlinedTextField(
                value = uiState.partName,
                onValueChange = viewModel::onPartNameChange,
                label = { Text("Part Name *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.partNameError != null,
                supportingText = uiState.partNameError?.let { { Text(it) } }
            )

            OutlinedTextField(
                value = uiState.partNumber,
                onValueChange = viewModel::onPartNumberChange,
                label = { Text("Part Number (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.brand,
                onValueChange = viewModel::onBrandChange,
                label = { Text("Brand (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.quantity,
                onValueChange = viewModel::onQuantityChange,
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = uiState.cost,
                onValueChange = viewModel::onCostChange,
                label = { Text("Cost *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = uiState.costError != null,
                supportingText = uiState.costError?.let { { Text(it) } }
            )

            CurrencySelector(
                selected = uiState.currency,
                onSelect = viewModel::onCurrencyChange
            )

            OutlinedTextField(
                value = uiState.warranty,
                onValueChange = viewModel::onWarrantyChange,
                label = { Text("Warranty (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("e.g. 1 year or 12,000 miles") }
            )

            Button(
                onClick = { viewModel.savePart(onNavigateBack) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSaving
            ) {
                if (uiState.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Save Part")
                }
            }
        }
    }
}
