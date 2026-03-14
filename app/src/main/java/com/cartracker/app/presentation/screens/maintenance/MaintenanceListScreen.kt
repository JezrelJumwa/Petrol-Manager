package com.cartracker.app.presentation.screens.maintenance

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cartracker.app.data.model.MaintenanceScheduleEntity
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceListScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    onNavigateToAddSchedule: (Long) -> Unit,
    viewModel: MaintenanceListViewModel = hiltViewModel()
) {
    val schedules by viewModel.schedules.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    var editingSchedule by remember { mutableStateOf<MaintenanceScheduleEntity?>(null) }

    LaunchedEffect(vehicleId) {
        viewModel.loadSchedules(vehicleId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Maintenance Schedule") },
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
            FloatingActionButton(onClick = { onNavigateToAddSchedule(vehicleId) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Schedule")
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
            if (schedules.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No maintenance schedules yet")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(schedules) { schedule ->
                        MaintenanceScheduleItem(
                            schedule = schedule,
                            onEdit = { editingSchedule = schedule },
                            onDelete = { viewModel.deleteSchedule(schedule) }
                        )
                    }
                }
            }
        }
    }

    editingSchedule?.let { schedule ->
        EditMaintenanceDialog(
            schedule = schedule,
            onDismiss = { editingSchedule = null },
            onSave = { updated ->
                viewModel.updateSchedule(updated)
                editingSchedule = null
            }
        )
    }
}

@Composable
fun MaintenanceScheduleItem(
    schedule: MaintenanceScheduleEntity,
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = schedule.maintenanceType,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit schedule")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete schedule")
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            schedule.intervalMiles?.let {
                Text(
                    text = "Every $it miles",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            schedule.intervalMonths?.let {
                Text(
                    text = "Every $it months",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            schedule.nextDueMileage?.let {
                Text(
                    text = "Next due: ${it.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1,")} miles",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            schedule.nextDueDate?.let {
                Text(
                    text = "Next due: ${dateFormat.format(Date(it))}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            if (schedule.reminderEnabled) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Reminders enabled",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Composable
private fun EditMaintenanceDialog(
    schedule: MaintenanceScheduleEntity,
    onDismiss: () -> Unit,
    onSave: (MaintenanceScheduleEntity) -> Unit
) {
    var maintenanceType by remember(schedule.id) { mutableStateOf(schedule.maintenanceType) }
    var intervalMiles by remember(schedule.id) { mutableStateOf(schedule.intervalMiles?.toString().orEmpty()) }
    var intervalMonths by remember(schedule.id) { mutableStateOf(schedule.intervalMonths?.toString().orEmpty()) }
    var nextDueMileage by remember(schedule.id) { mutableStateOf(schedule.nextDueMileage?.toString().orEmpty()) }
    var reminderEnabled by remember(schedule.id) { mutableStateOf(schedule.reminderEnabled) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Schedule") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = maintenanceType, onValueChange = { maintenanceType = it }, label = { Text("Type") }, singleLine = true)
                OutlinedTextField(value = intervalMiles, onValueChange = { intervalMiles = it }, label = { Text("Interval Miles") }, singleLine = true)
                OutlinedTextField(value = intervalMonths, onValueChange = { intervalMonths = it }, label = { Text("Interval Months") }, singleLine = true)
                OutlinedTextField(value = nextDueMileage, onValueChange = { nextDueMileage = it }, label = { Text("Next Due Mileage") }, singleLine = true)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Reminder Enabled")
                    Switch(checked = reminderEnabled, onCheckedChange = { reminderEnabled = it })
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    schedule.copy(
                        maintenanceType = maintenanceType.trim().ifBlank { schedule.maintenanceType },
                        intervalMiles = intervalMiles.toIntOrNull(),
                        intervalMonths = intervalMonths.toIntOrNull(),
                        nextDueMileage = nextDueMileage.toIntOrNull(),
                        reminderEnabled = reminderEnabled
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
