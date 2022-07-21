package br.com.alaksion.feature_login.presentation.login.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import br.com.alaksion.core_ui.components.LoaderButton
import br.com.alaksion.core_ui.components.VerticalSpacer
import br.com.alaksion.core_ui.theme.NoteSpacings
import br.com.alaksion.feature_login.R
import br.com.alaksion.feature_login.presentation.model.PasswordVisibility

@ExperimentalComposeUiApi
@Composable
internal fun LoginForm(
    email: String,
    password: String,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    passwordVisibility: PasswordVisibility,
    toggleShowPassword: () -> Unit,
    submitLogin: () -> Unit,
    isButtonLoading: Boolean,
    isButtonEnabled: Boolean
) {
    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = onChangeEmail,
        placeholder = { Text(stringResource(id = R.string.email_placeholder)) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focus.moveFocus(FocusDirection.Down) }
        )
    )
    VerticalSpacer(height = NoteSpacings.spacings.small)
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = onChangePassword,
        visualTransformation = passwordVisibility.transformation,
        trailingIcon = {
            IconButton(onClick = toggleShowPassword) {
                Icon(
                    painter = painterResource(id = passwordVisibility.icon),
                    contentDescription = null
                )
            }
        },
        placeholder = { Text(stringResource(id = R.string.password_placeholder)) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onNext = { keyboard?.hide() }
        )
    )
    VerticalSpacer(height = NoteSpacings.spacings.small)
    LoaderButton(
        onClick = submitLogin,
        isLoading = isButtonLoading,
        modifier = Modifier.fillMaxWidth(),
        enabled = isButtonEnabled && isButtonLoading.not()
    ) {
        Text(stringResource(id = R.string.login))
    }
}