package com.baeolian.idealisto.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.baeolian.idealisto.view.navigation.graphs.MainNavigation
import com.baeolian.idealisto.view.navigation.graphs.addMainGraph

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainNavigation.HomePage.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        addMainGraph(navController = navController)
    }
}
