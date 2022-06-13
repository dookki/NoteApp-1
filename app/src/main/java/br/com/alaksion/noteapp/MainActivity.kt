package br.com.alaksion.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.alaksion.core_ui.theme.NoteAppTheme
import br.com.alaksion.feature_login.presentation.NavAuthNavModule
import com.example.navigation.AuthRouter
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
                    NavHost(navController = controller, startDestination = AuthRouter.Graph.route) {
                        NavAuthNavModule(controller)
                    }
                }
            }
        }
    }

    override val di: DI
        get() = AppDi
}