package com.example.navigation

interface NavRouter {
    val route: String
}

private const val GraphRoute = "auth"

sealed class AuthRouter(override val route: String) : NavRouter {
    object Graph : AuthRouter(GraphRoute)
    object Login : AuthRouter("$GraphRoute/login")
    object Registration : AuthRouter("$GraphRoute/registration")
    object RegistrationSuccess: AuthRouter("$GraphRoute/registration/success")
}