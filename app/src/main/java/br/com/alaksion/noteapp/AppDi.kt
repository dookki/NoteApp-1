package br.com.alaksion.noteapp

import br.com.alaksion.authentication.FirebaseAuthDi
import br.com.alaksion.feature_home.homeDi
import br.com.alaksion.feature_login.presentation.AuthDi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

private val MainDi = DI.Module("main") {

    bind<MainViewModel>() with singleton {
        MainViewModel(
            auth = instance()
        )
    }

}

val AppDi = DI {
    import(MainDi)
    import(FirebaseAuthDi)
    import(AuthDi)
    import(homeDi)
}