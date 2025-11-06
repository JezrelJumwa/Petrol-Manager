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
    object ExpenseList : Screen("expense_list/{vehicleId}") {
        fun createRoute(vehicleId: Long) = "expense_list/$vehicleId"
    }
}
