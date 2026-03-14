package com.cartracker.app.presentation.screens.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cartracker.app.presentation.components.CurrencySelector
import com.cartracker.app.presentation.components.formatAmount
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cartracker.app.data.model.PartEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartsListScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    viewModel: PartsListViewModel = hiltViewModel()
) {
    val parts by viewModel.parts.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    var editingPart by remember { mutableStateOf<PartEntity?>(null) }

    LaunchedEffect(vehicleId) {
        viewModel.loadParts(vehicleId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parts Tracking") },
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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (parts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No parts recorded yet")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(parts, key = { it.id }) { part ->
                    PartItem(
                        part = part,
                        onEdit = { editingPart = part },
                        onDelete = { viewModel.deletePart(part) }
                    )
                }
            }
        }
    }

    editingPart?.let { part ->
        EditPartDialog(
            part = part,
            onDismiss = { editingPart = null },
            onSave = { updated ->
                viewModel.updatePart(updated)
                editingPart = null
            }
        )
    }
}

@Composable
private fun PartItem(
    part: PartEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = part.partName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = formatAmount(part.cost, part.currency),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit part")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete part")
                    }
                }
            }

            part.brand?.let {
                Text(
                    text = "Brand: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            part.partNumber?.let {
                Text(
                    text = "Part #: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "Qty: ${part.quantity}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            part.warranty?.let {
                Text(
                    text = "Warranty: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun EditPartDialog(
    part: PartEntity,
    onDismiss: () -> Unit,
    onSave: (PartEntity) -> Unit
) {
    var partName by remember(part.id) { mutableStateOf(part.partName) }
    var partNumber by remember(part.id) { mutableStateOf(part.partNumber.orEmpty()) }
    var brand by remember(part.id) { mutableStateOf(part.brand.orEmpty()) }
    var quantity by remember(part.id) { mutableStateOf(part.quantity.toString()) }
    var cost by remember(part.id) { mutableStateOf(part.cost.toString()) }
    var warranty by remember(part.id) { mutableStateOf(part.warranty.orEmpty()) }
    var currency by remember(part.id) { mutableStateOf(part.currency) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Part") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = partName, onValueChange = { partName = it }, label = { Text("Part Name") }, singleLine = true)
                OutlinedTextField(value = partNumber, onValueChange = { partNumber = it }, label = { Text("Part Number") }, singleLine = true)
                OutlinedTextField(value = brand, onValueChange = { brand = it }, label = { Text("Brand") }, singleLine = true)
                OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") }, singleLine = true)
                OutlinedTextField(value = cost, onValueChange = { cost = it }, label = { Text("Cost") }, singleLine = true)
                CurrencySelector(selected = currency, onSelect = { currency = it })
                OutlinedTextField(value = warranty, onValueChange = { warranty = it }, label = { Text("Warranty") }, singleLine = true)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    part.copy(
                        partName = partName.trim().ifBlank { part.partName },
                        partNumber = partNumber.trim().takeIf { it.isNotBlank() },
                        brand = brand.trim().takeIf { it.isNotBlank() },
                        quantity = quantity.toIntOrNull() ?: part.quantity,
                        cost = cost.toDoubleOrNull() ?: part.cost,
                        currency = currency,
                        warranty = warranty.trim().takeIf { it.isNotBlank() }
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
