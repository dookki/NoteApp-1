package br.com.alaksion.feature_login.presentation.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController
import br.com.alaksion.authentication.GoogleAuthContract
import br.com.alaksion.core_ui.components.VerticalSpacer
import br.com.alaksion.core_ui.components.WeightSpacer
import br.com.alaksion.core_ui.theme.NoteAppTheme
import br.com.alaksion.core_ui.theme.NoteSpacings
import br.com.alaksion.feature_login.R
import br.com.alaksion.feature_login.presentation.login.components.LoginForm
import br.com.alaksion.feature_login.presentation.login.components.SocialLogins
import br.com.alaksion.feature_login.presentation.login.models.LoginState
import br.com.alaksion.feature_login.presentation.login.models.LoginStateProvider
import br.com.alaksion.utils.injection.rememberViewModel
import com.example.navigation.AuthRouter
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
internal fun LoginScreen(
    navigator: NavController
) {
    val state = rememberScaffoldState()
    val viewModel = rememberViewModel<LoginViewModel>()

    val screenState by viewModel.state.collectAsState()

    val socialLoginIntent =
        rememberLauncherForActivityResult(contract = GoogleAuthContract()) { task ->
            viewModel.handleSocialLogin(task?.result)
        }

    LoginScreenContent(
        onChangeEmail = { viewModel.onChangeEmail(it) },
        onChangePassword = { viewModel.onChangePassword(it) },
        toggleShowPassword = { viewModel.togglePasswordVisibility() },
        submitLogin = { viewModel.submitLogin() },
        submitGoogleLogin = {
            socialLoginIntent.launch(1)
        },
        scaffoldState = state,
        state = screenState,
        navigateToRegister = {
            navigator.navigate(AuthRouter.Registration.route)
        }
    )

    LaunchedEffect(key1 = viewModel) {
        viewModel.events.collectLatest {
            when (it) {
                is LoginEvents.LoginSuccess -> {
                    state.snackbarHostState.showSnackbar(
                        "Login succeeded"
                    )
                }
                is LoginEvents.LoginError -> {
                    state.snackbarHostState.showSnackbar(
                        "Login failed"
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun LoginScreenContent(
    state: LoginState,
    scaffoldState: ScaffoldState,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    toggleShowPassword: () -> Unit,
    submitLogin: () -> Unit,
    submitGoogleLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(NoteSpacings.spacings.large),
            verticalArrangement = Arrangement.Center
        ) {
            WeightSpacer(weight = 1f)
            LoginForm(
                email = state.email,
                password = state.password,
                onChangeEmail = onChangeEmail,
                onChangePassword = onChangePassword,
                passwordVisibility = state.passwordVisibility,
                toggleShowPassword = toggleShowPassword,
                submitLogin = submitLogin,
                isButtonLoading = state.isButtonLoading,
                isButtonEnabled = state.isButtonEnabled
            )
            VerticalSpacer(height = NoteSpacings.spacings.small)
            Text(
                stringResource(id = R.string.login_or),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            VerticalSpacer(height = NoteSpacings.spacings.small)
            SocialLogins(
                modifier = Modifier.fillMaxWidth(),
                onGoogleClick = submitGoogleLogin
            )
            WeightSpacer(weight = 1f)
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = navigateToRegister
            ) {
                Text(
                    text = stringResource(R.string.not_a_member),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            VerticalSpacer(height = NoteSpacings.spacings.medium)
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
private fun LoginScreenContentPreview(
    @PreviewParameter(LoginStateProvider::class) state: LoginState
) {
    NoteAppTheme {
        LoginScreenContent(
            onChangeEmail = {},
            onChangePassword = {},
            toggleShowPassword = {},
            submitLogin = {},
            submitGoogleLogin = {},
            scaffoldState = rememberScaffoldState(),
            state = state,
            navigateToRegister = {}
        )
    }
}
