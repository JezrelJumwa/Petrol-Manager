package com.cartracker.app.presentation.navigation

sealed class Screen(val route: String) {
    object VehicleList : Screen("vehicle_list")
    object AddVehicle : Screen("add_vehicle")
    object VehicleDetail : Screen("vehicle_detail/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "vehicle_detail/$vehicleId"
    }
    object ServiceList : Screen("service_list/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "service_list/$vehicleId"
    }
    object AddService : Screen("add_service/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "add_service/$vehicleId"
    }
    object MaintenanceList : Screen("maintenance_list/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "maintenance_list/$vehicleId"
    }
    object AddMaintenance : Screen("add_maintenance/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "add_maintenance/$vehicleId"
    }
    object MileageLogs : Screen("mileage_logs/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "mileage_logs/$vehicleId"
    }
    object PartsList : Screen("parts_list/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "parts_list/$vehicleId"
    }
    object AddPart : Screen("add_part/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "add_part/$vehicleId"
    }
    object ExpenseList : Screen("expense_list/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "expense_list/$vehicleId"
    }
    object AddExpense : Screen("add_expense/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "add_expense/$vehicleId"
    }
}
