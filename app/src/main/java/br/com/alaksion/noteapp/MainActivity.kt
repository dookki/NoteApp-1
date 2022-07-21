package br.com.alaksion.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.alaksion.core_ui.theme.NoteAppTheme
import br.com.alaksion.feature_home.HomeNavModule
import br.com.alaksion.feature_login.presentation.NavAuthNavModule
import br.com.alaksion.utils.injection.rememberViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.compose.withDI

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity(), DIAware {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                withDI(AppDi) {
                    val controller = rememberNavController()
                    val viewModel = rememberViewModel<MainViewModel>()
                    NavHost(
                        navController = controller,
                        startDestination = viewModel.getStartDestination().graph
                    ) {
                        NavAuthNavModule(controller)
                        HomeNavModule(controller)
                    }
                }
            }
        }
    }

    override val di: DI
        get() = AppDi
}