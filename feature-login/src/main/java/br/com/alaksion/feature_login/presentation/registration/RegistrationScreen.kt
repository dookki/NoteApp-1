package br.com.alaksion.feature_login.presentation.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import br.com.alaksion.core_ui.components.LoaderButton
import br.com.alaksion.core_ui.components.VerticalSpacer
import br.com.alaksion.core_ui.theme.NoteSpacings
import br.com.alaksion.feature_login.R
import br.com.alaksion.feature_login.presentation.registration.components.RegistrationForm
import br.com.alaksion.utils.injection.rememberViewModel
import com.example.navigation.AuthRouter
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
internal fun RegistrationScreen(
    navigator: NavController
) {
    val state = rememberScaffoldState()
    val viewModel = rememberViewModel<RegistrationViewModel>()

    RegistrationContent(
        scaffoldState = state,
        state = viewModel.state.collectAsState().value,
        updateEmail = viewModel::updateEmail,
        updateConfirmEmail = viewModel::updateConfirmEmail,
        updatePassword = viewModel::updatePassword,
        toggleShowPassword = viewModel::toggleShowPassword,
        goBack = navigator::popBackStack,
        submitLogin = viewModel::createAccount
    )

    LaunchedEffect(key1 = viewModel, key2 = state) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is RegistrationEvents.Error -> {
                    state.snackbarHostState.showSnackbar(message = event.message)
                }
                is RegistrationEvents.NavigateToHome -> {
                    navigator.navigate(
                        AuthRouter.Routes.RegistrationSuccess.path
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun RegistrationContent(
    scaffoldState: ScaffoldState,
    state: RegistrationState,
    updateEmail: (String) -> Unit,
    updateConfirmEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    toggleShowPassword: () -> Unit,
    goBack: () -> Unit,
    submitLogin: () -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar() {
                IconButton(onClick = goBack) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(NoteSpacings.spacings.large)
        ) {
            Text(
                text = stringResource(id = R.string.registration),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Medium
            )
            VerticalSpacer(height = NoteSpacings.spacings.medium)
            RegistrationForm(
                email = state.email,
                changeEmail = updateEmail,
                confirmEmail = state.confirmEmail,
                changeConfirmEmail = updateConfirmEmail,
                password = state.password,
                changePassword = updatePassword,
                passwordVisibility = state.passwordVisibility,
                toggleShowPassword = toggleShowPassword,
            )
            LoaderButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = submitLogin,
                isLoading = state.isButtonLoading,
                enabled = state.isButtonEnabled
            ) {
                Text(text = stringResource(id = R.string.create_account))
            }
        }
    }
}