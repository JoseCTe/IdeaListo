package com.baeolian.idealisto.view.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.baeolian.idealisto.view.screen.HomeScreen
import com.baeolian.idealisto.view.screen.PropertyDetailsScreen

sealed class MainNavigation(val route: String) {
    data object HomePage : MainNavigation("home_main_screen")
    data object PropertyDetailsPage : MainNavigation("property_details_screen/{$PROPERTY_ID}")
}

fun NavGraphBuilder.addMainGraph(navController: NavHostController) {
    composable(route = MainNavigation.HomePage.route) {
        HomeScreen(
            navigateToDetailsScreen = { propertyId ->
                navController.navigate("property_details_screen/$propertyId")
            }
        )
    }

    composable(route = MainNavigation.PropertyDetailsPage.route) {
        PropertyDetailsScreen(
            navigateBack = { navController.navigateUp() }
        )
    }
}

private const val PROPERTY_ID = "propertyId"
