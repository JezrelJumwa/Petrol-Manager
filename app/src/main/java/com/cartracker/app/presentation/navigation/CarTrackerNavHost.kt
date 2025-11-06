package com.cartracker.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cartracker.app.presentation.screens.vehicle.AddVehicleScreen
import com.cartracker.app.presentation.screens.vehicle.VehicleDetailScreen
import com.cartracker.app.presentation.screens.vehicle.VehicleListScreen

@Composable
fun CarTrackerNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.VehicleList.route
    ) {
        composable(Screen.VehicleList.route) {
            VehicleListScreen(
                onNavigateToAddVehicle = {
                    navController.navigate(Screen.AddVehicle.route)
                },
                onNavigateToVehicleDetail = { vehicleId ->
                    navController.navigate(Screen.VehicleDetail.createRoute(vehicleId))
                }
            )
        }

        composable(Screen.AddVehicle.route) {
            AddVehicleScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.VehicleDetail.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            VehicleDetailScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToServices = { vId ->
                    navController.navigate(Screen.ServiceList.createRoute(vId))
                },
                onNavigateToMaintenance = { vId ->
                    navController.navigate(Screen.MaintenanceList.createRoute(vId))
                },
                onNavigateToExpenses = { vId ->
                    navController.navigate(Screen.ExpenseList.createRoute(vId))
                }
            )
        }
    }
}
