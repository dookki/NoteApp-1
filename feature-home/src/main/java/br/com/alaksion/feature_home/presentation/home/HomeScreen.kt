package br.com.alaksion.feature_home.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.alaksion.utils.injection.rememberViewModel
import com.example.navigation.AuthRouter
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun HomeScreen(
    navigator: NavController
) {
    val viewModel = rememberViewModel<HomeScreenViewModel>()

    EventHandler(viewModel, navigator)
    Content(
        state = viewModel.state.collectAsState().value,
        onIntentCalled = { viewModel.handleIntent(it) }
    )
}

@Composable
private fun Content(
    state: HomeState,
    onIntentCalled: (HomeIntent) -> Unit
) {
    Scaffold {
        Column {
            var counterState: Int by remember {
                mutableStateOf(0)
            }

            val derivedText = remember {
                derivedStateOf {
                    "Counter state is $counterState"
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { counterState++ }
            ) {
                Text("INCREMENT BUTTON")
            }
            Text(derivedText.value)

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onIntentCalled(HomeIntent.Logout) }
            ) {
                Text("Logout")
            }
        }
    }
}

@Composable
private fun EventHandler(
    model: HomeScreenViewModel,
    navigator: NavController
) {
    LaunchedEffect(key1 = model) {
        model.events.collectLatest {
            when (it) {
                is HomeScreenEvents.Logout -> {
                    navigator.navigate(AuthRouter.Routes.Login.path) {
                        this.popUpTo(AuthRouter.Routes.Login.path)
                    }
                }
            }
        }
    }
}