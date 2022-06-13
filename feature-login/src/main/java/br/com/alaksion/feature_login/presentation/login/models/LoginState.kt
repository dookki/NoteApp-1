package br.com.alaksion.feature_login.presentation.login.models

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.alaksion.feature_login.presentation.model.PasswordVisibility

internal data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: PasswordVisibility = PasswordVisibility.Hidden,
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)

internal class LoginStateProvider : PreviewParameterProvider<LoginState> {
    override val values: Sequence<LoginState>
        get() = sequenceOf(
            LoginState("email", "password", PasswordVisibility.Hidden, true),
            LoginState("email", "password", PasswordVisibility.Visible, false)
        )
}