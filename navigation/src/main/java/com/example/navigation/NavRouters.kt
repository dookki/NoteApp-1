package com.example.navigation

interface NavRouter {
    val graph: String
}

object AuthRouter : NavRouter {

    override val graph: String get() = "auth"

    enum class Routes(val path: String) {
        Login("$graph/login"),
        Registration("$graph/registration"),
        RegistrationSuccess("$graph/registration/success")
    }
}

object HomeRouter : NavRouter {

    override val graph: String get() = "home"

    enum class Routes(val path: String) {
        Home("$graph/home"),

    }
}