package br.com.alaksion.feature_login.presentation.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import br.com.alaksion.feature_login.R
import br.com.alaksion.feature_login.presentation.model.PasswordVisibility

@ExperimentalComposeUiApi
@Composable
internal fun RegistrationForm(
    modifier: Modifier = Modifier,
    email: String,
    changeEmail: (String) -> Unit,
    confirmEmail: String,
    changeConfirmEmail: (String) -> Unit,
    password: String,
    changePassword: (String) -> Unit,
    passwordVisibility: PasswordVisibility,
    toggleShowPassword: () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val (emailFocus, confirmEmailFocus, passwordFocus) = FocusRequester.createRefs()

    Column(modifier) {
        Field(
            value = email,
            onChange = changeEmail,
            focusRequester = emailFocus,
            onIme = { confirmEmailFocus.requestFocus() },
            label = stringResource(id = R.string.email_placeholder),
            placeholder = stringResource(id = R.string.email_placeholder)
        )
        Field(
            value = confirmEmail,
            onChange = changeConfirmEmail,
            focusRequester = confirmEmailFocus,
            onIme = { passwordFocus.requestFocus() },
            label = stringResource(id = R.string.confirm_email_placeholder),
            placeholder = stringResource(id = R.string.confirm_email_placeholder)
        )
        PasswordField(
            value = password,
            onChange = changePassword,
            focusRequester = passwordFocus,
            onIme = { keyboard?.hide() },
            visibility = passwordVisibility,
            toggleShowPassword = toggleShowPassword
        )
    }

}

@Composable
private fun PasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onIme: () -> Unit,
    visibility: PasswordVisibility,
    toggleShowPassword: () -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onChange,
        visualTransformation = visibility.transformation,
        trailingIcon = {
            IconButton(onClick = toggleShowPassword) {
                Icon(
                    painter = painterResource(id = visibility.icon),
                    contentDescription = null
                )
            }
        },
        label = {
            Text(text = stringResource(id = R.string.password_placeholder))
        },
        placeholder = { Text(stringResource(id = R.string.password_placeholder)) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onIme() }
        ),
    )
}

@Composable
private fun Field(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onIme: () -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(placeholder)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { onIme() }
        ),
    )
}