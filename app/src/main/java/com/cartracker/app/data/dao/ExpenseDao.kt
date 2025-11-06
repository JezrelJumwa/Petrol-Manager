package com.cartracker.app.data.dao

import androidx.room.*
import com.cartracker.app.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses WHERE vehicleId = :vehicleId ORDER BY expenseDate DESC")
    fun getExpensesByVehicle(vehicleId: Long): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE vehicleId = :vehicleId AND category = :category ORDER BY expenseDate DESC")
    fun getExpensesByCategory(vehicleId: Long, category: String): Flow<List<ExpenseEntity>>

    @Query("""
        SELECT * FROM expenses 
        WHERE vehicleId = :vehicleId 
        AND expenseDate BETWEEN :startDate AND :endDate 
        ORDER BY expenseDate DESC
    """)
    fun getExpensesInDateRange(vehicleId: Long, startDate: Long, endDate: Long): Flow<List<ExpenseEntity>>

    @Query("SELECT SUM(amount) FROM expenses WHERE vehicleId = :vehicleId")
    suspend fun getTotalExpenses(vehicleId: Long): Double?

    @Query("SELECT SUM(amount) FROM expenses WHERE vehicleId = :vehicleId AND category = :category")
    suspend fun getTotalExpensesByCategory(vehicleId: Long, category: String): Double?

    @Query("""
        SELECT SUM(amount) FROM expenses 
        WHERE vehicleId = :vehicleId 
        AND expenseDate BETWEEN :startDate AND :endDate
    """)
    suspend fun getTotalExpensesInDateRange(vehicleId: Long, startDate: Long, endDate: Long): Double?

    @Query("SELECT DISTINCT category FROM expenses WHERE vehicleId = :vehicleId ORDER BY category")
    fun getExpenseCategories(vehicleId: Long): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)
}
