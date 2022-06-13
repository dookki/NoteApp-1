package br.com.alaksion.feature_login.presentation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alaksion.feature_login.presentation.login.LoginScreen
import br.com.alaksion.feature_login.presentation.registration.RegistrationScreen
import br.com.alaksion.feature_login.presentation.success.RegistrationSuccessScreen
import com.example.navigation.AuthRouter

@ExperimentalComposeUiApi
fun NavGraphBuilder.NavAuthNavModule(
    controller: NavController
) {
    navigation(startDestination = AuthRouter.Login.route, route = AuthRouter.Graph.route) {
        composable(AuthRouter.Login.route) {
            LoginScreen(controller)
        }

        composable(AuthRouter.Registration.route) {
            RegistrationScreen(controller)
        }

        composable(AuthRouter.RegistrationSuccess.route) {
            RegistrationSuccessScreen(navigator = controller)
        }

    }

}