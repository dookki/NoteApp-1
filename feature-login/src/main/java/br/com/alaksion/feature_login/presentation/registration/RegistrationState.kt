package br.com.alaksion.feature_login.presentation.registration

import br.com.alaksion.feature_login.presentation.model.PasswordVisibility

internal data class RegistrationState(
    val email: String = "",
    val confirmEmail: String = "",
    val password: String = "",
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val passwordVisibility: PasswordVisibility = PasswordVisibility.Hidden,
)
