package com.cartracker.app.presentation.screens.expense

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
import com.cartracker.app.data.model.ExpenseEntity
import com.cartracker.app.presentation.components.CurrencySelector
import com.cartracker.app.presentation.components.formatAmount
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(
    vehicleId: Long,
    onNavigateBack: () -> Unit,
    onNavigateToAddExpense: (Long) -> Unit = {},
    viewModel: ExpenseListViewModel = hiltViewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val totalExpenses by viewModel.totalExpenses.collectAsState()
    var editingExpense by remember { mutableStateOf<ExpenseEntity?>(null) }

    LaunchedEffect(vehicleId) {
        viewModel.loadExpenses(vehicleId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expenses") },
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
            FloatingActionButton(onClick = { onNavigateToAddExpense(vehicleId) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
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
                // Total expenses card
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
                            text = "Total Expenses",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "%,.2f".format(totalExpenses),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                if (expenses.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No expenses yet")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(expenses) { expense ->
                            ExpenseItem(
                                expense = expense,
                                onEdit = { editingExpense = expense },
                                onDelete = { viewModel.deleteExpense(expense) }
                            )
                        }
                    }
                }
            }
        }
    }

    editingExpense?.let { expense ->
        EditExpenseDialog(
            expense = expense,
            onDismiss = { editingExpense = null },
            onSave = { updated ->
                viewModel.updateExpense(updated)
                editingExpense = null
            }
        )
    }
}

@Composable
fun ExpenseItem(
    expense: ExpenseEntity,
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
                        text = expense.category,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = formatAmount(expense.amount, expense.currency),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit expense")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete expense")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = dateFormat.format(Date(expense.expenseDate)),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            expense.vendor?.let {
                Text(
                    text = "Vendor: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            expense.mileageAtExpense?.let {
                Text(
                    text = "${it.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1,")} miles",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            expense.notes?.let {
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
private fun EditExpenseDialog(
    expense: ExpenseEntity,
    onDismiss: () -> Unit,
    onSave: (ExpenseEntity) -> Unit
) {
    var category by remember(expense.id) { mutableStateOf(expense.category) }
    var amount by remember(expense.id) { mutableStateOf(expense.amount.toString()) }
    var vendor by remember(expense.id) { mutableStateOf(expense.vendor.orEmpty()) }
    var mileage by remember(expense.id) { mutableStateOf(expense.mileageAtExpense?.toString().orEmpty()) }
    var notes by remember(expense.id) { mutableStateOf(expense.notes.orEmpty()) }
    var currency by remember(expense.id) { mutableStateOf(expense.currency) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Expense") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, singleLine = true)
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") }, singleLine = true)
                CurrencySelector(selected = currency, onSelect = { currency = it })
                OutlinedTextField(value = vendor, onValueChange = { vendor = it }, label = { Text("Vendor") }, singleLine = true)
                OutlinedTextField(value = mileage, onValueChange = { mileage = it }, label = { Text("Mileage") }, singleLine = true)
                OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes") }, minLines = 2)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    expense.copy(
                        category = category.trim().ifBlank { expense.category },
                        amount = amount.toDoubleOrNull() ?: expense.amount,
                        currency = currency,
                        vendor = vendor.trim().takeIf { it.isNotBlank() },
                        mileageAtExpense = mileage.toIntOrNull(),
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
