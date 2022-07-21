package br.com.alaksion.feature_home

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alaksion.feature_home.presentation.home.HomeScreen
import com.example.navigation.HomeRouter

@ExperimentalComposeUiApi
fun NavGraphBuilder.HomeNavModule(
    controller: NavController
) {
    navigation(
        startDestination = HomeRouter.Routes.Home.path,
        route = HomeRouter.graph
    ) {
        composable(HomeRouter.Routes.Home.path) {
            HomeScreen(navigator = controller)
        }
    }
}