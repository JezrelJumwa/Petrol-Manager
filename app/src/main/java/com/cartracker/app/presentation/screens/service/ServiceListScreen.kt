package com.cartracker.app.presentation.screens.service

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cartracker.app.data.model.ServiceRecordEntity
import com.cartracker.app.presentation.components.CurrencySelector
import com.cartracker.app.presentation.components.formatAmount
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceListScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    onNavigateToAddService: (Long) -> Unit,
    viewModel: ServiceListViewModel = hiltViewModel()
) {
    val serviceRecords by viewModel.serviceRecords.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val totalCost by viewModel.totalCost.collectAsState()
    var editingRecord by remember { mutableStateOf<ServiceRecordEntity?>(null) }

    LaunchedEffect(vehicleId) {
        viewModel.loadServiceRecords(vehicleId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Service History") },
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToAddService(vehicleId) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Service")
            }
        }
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Total cost card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Total Service Cost",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "%,.2f".format(totalCost),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                if (serviceRecords.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No service records yet")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(serviceRecords) { record ->
                            ServiceRecordItem(
                                record = record,
                                onEdit = { editingRecord = record },
                                onDelete = { viewModel.deleteServiceRecord(record) }
                            )
                        }
                    }
                }
            }
        }
    }

    editingRecord?.let { record ->
        EditServiceDialog(
            record = record,
            onDismiss = { editingRecord = null },
            onSave = { updated ->
                viewModel.updateServiceRecord(updated)
                editingRecord = null
            }
        )
    }
}

@Composable
fun ServiceRecordItem(
    record: ServiceRecordEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = record.serviceType,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = formatAmount(record.cost, record.currency),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit service")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete service")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = dateFormat.format(Date(record.serviceDate)),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Text(
                text = "${record.mileageAtService.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1,")} miles",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            record.servicedBy?.let {
                Text(
                    text = "Serviced by: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            record.notes?.let {
                if (it.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun EditServiceDialog(
    record: ServiceRecordEntity,
    onDismiss: () -> Unit,
    onSave: (ServiceRecordEntity) -> Unit
) {
    var serviceType by remember(record.id) { mutableStateOf(record.serviceType) }
    var mileage by remember(record.id) { mutableStateOf(record.mileageAtService.toString()) }
    var cost by remember(record.id) { mutableStateOf(record.cost.toString()) }
    var servicedBy by remember(record.id) { mutableStateOf(record.servicedBy.orEmpty()) }
    var notes by remember(record.id) { mutableStateOf(record.notes.orEmpty()) }
    var currency by remember(record.id) { mutableStateOf(record.currency) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Service") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = serviceType, onValueChange = { serviceType = it }, label = { Text("Service Type") }, singleLine = true)
                OutlinedTextField(value = mileage, onValueChange = { mileage = it }, label = { Text("Mileage") }, singleLine = true)
                OutlinedTextField(value = cost, onValueChange = { cost = it }, label = { Text("Cost") }, singleLine = true)
                CurrencySelector(selected = currency, onSelect = { currency = it })
                OutlinedTextField(value = servicedBy, onValueChange = { servicedBy = it }, label = { Text("Serviced By") }, singleLine = true)
                OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes") }, minLines = 2)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    record.copy(
                        serviceType = serviceType.trim().ifBlank { record.serviceType },
                        mileageAtService = mileage.toIntOrNull() ?: record.mileageAtService,
                        cost = cost.toDoubleOrNull() ?: record.cost,
                        currency = currency,
                        servicedBy = servicedBy.trim().takeIf { it.isNotBlank() },
                        notes = notes.trim().takeIf { it.isNotBlank() }
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
