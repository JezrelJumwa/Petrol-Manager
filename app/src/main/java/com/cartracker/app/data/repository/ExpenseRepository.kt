package com.cartracker.app.data.repository

import com.cartracker.app.data.dao.ExpenseDao
import com.cartracker.app.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) {
    fun getExpensesByVehicle(vehicleId: Long): Flow<List<ExpenseEntity>> =
        expenseDao.getExpensesByVehicle(vehicleId)

    fun getExpensesByCategory(vehicleId: Long, category: String): Flow<List<ExpenseEntity>> =
        expenseDao.getExpensesByCategory(vehicleId, category)

    fun getExpenseCategories(vehicleId: Long): Flow<List<String>> =
        expenseDao.getExpenseCategories(vehicleId)

    suspend fun getTotalExpenses(vehicleId: Long): Double =
        expenseDao.getTotalExpenses(vehicleId) ?: 0.0

    suspend fun getTotalExpensesByCategory(vehicleId: Long, category: String): Double =
        expenseDao.getTotalExpensesByCategory(vehicleId, category) ?: 0.0

    suspend fun insertExpense(expense: ExpenseEntity): Long =
        expenseDao.insertExpense(expense)

    suspend fun updateExpense(expense: ExpenseEntity) =
        expenseDao.updateExpense(expense)

    suspend fun deleteExpense(expense: ExpenseEntity) =
        expenseDao.deleteExpense(expense)
}
