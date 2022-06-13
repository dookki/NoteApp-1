package br.com.alaksion.feature_login.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import br.com.alaksion.feature_login.R

internal enum class PasswordVisibility(
    @DrawableRes val icon: Int,
    val transformation: VisualTransformation
) {
    Visible(R.drawable.ic_visibility_off, VisualTransformation.None),
    Hidden(R.drawable.ic_visibility, PasswordVisualTransformation());

    fun toggle(): PasswordVisibility {
        return if (this == Visible) Hidden else Visible
    }
}