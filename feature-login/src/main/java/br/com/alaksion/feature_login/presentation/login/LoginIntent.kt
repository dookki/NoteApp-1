package br.com.alaksion.feature_login.presentation.login

internal sealed class LoginIntent {
    data class UpdateEmail(val newValue: String) : LoginIntent()
    data class UpdatePassword(val newValue: String) : LoginIntent()
    object TogglePassword : LoginIntent()
    object SubmitLogin : LoginIntent()
    object SubmitGoogleLogin : LoginIntent()
    object GoToRegister : LoginIntent()
}
