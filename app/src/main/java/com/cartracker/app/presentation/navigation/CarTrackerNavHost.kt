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
import com.cartracker.app.presentation.screens.service.ServiceListScreen
import com.cartracker.app.presentation.screens.service.AddServiceScreen
import com.cartracker.app.presentation.screens.maintenance.MaintenanceListScreen
import com.cartracker.app.presentation.screens.maintenance.AddMaintenanceScreen
import com.cartracker.app.presentation.screens.expense.ExpenseListScreen
import com.cartracker.app.presentation.screens.expense.AddExpenseScreen
import com.cartracker.app.presentation.screens.mileage.MileageLogScreen
import com.cartracker.app.presentation.screens.parts.PartsListScreen
import com.cartracker.app.presentation.screens.parts.AddPartScreen

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
                onNavigateToMileageLogs = { vId ->
                    navController.navigate(Screen.MileageLogs.createRoute(vId))
                },
                onNavigateToParts = { vId ->
                    navController.navigate(Screen.PartsList.createRoute(vId))
                },
                onNavigateToExpenses = { vId ->
                    navController.navigate(Screen.ExpenseList.createRoute(vId))
                }
            )
        }

        composable(
            route = Screen.ServiceList.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            ServiceListScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToAddService = { vId ->
                    navController.navigate(Screen.AddService.createRoute(vId))
                }
            )
        }

        composable(
            route = Screen.AddService.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            AddServiceScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.MaintenanceList.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            MaintenanceListScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToAddSchedule = { vId ->
                    navController.navigate(Screen.AddMaintenance.createRoute(vId))
                }
            )
        }

        composable(
            route = Screen.AddMaintenance.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            AddMaintenanceScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.MileageLogs.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            MileageLogScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.PartsList.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            PartsListScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToAddPart = { vId ->
                    navController.navigate(Screen.AddPart.createRoute(vId))
                }
            )
        }

        composable(
            route = Screen.AddPart.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            AddPartScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.ExpenseList.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            ExpenseListScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToAddExpense = { vId ->
                    navController.navigate(Screen.AddExpense.createRoute(vId))
                }
            )
        }

        composable(
            route = Screen.AddExpense.route,
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType })
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getLong("vehicleId") ?: return@composable
            AddExpenseScreen(
                vehicleId = vehicleId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
