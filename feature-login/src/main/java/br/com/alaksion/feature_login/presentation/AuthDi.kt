package br.com.alaksion.feature_login.presentation

import br.com.alaksion.feature_login.presentation.login.LoginViewModel
import br.com.alaksion.feature_login.presentation.registration.RegistrationViewModel
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val AuthDi = DI.Module("feature-auth") {

    bind<LoginViewModel>() with provider {
        LoginViewModel(
            firebaseAuth = instance(),
            dispatcher = Dispatchers.Default
        )
    }

    bind<RegistrationViewModel>() with provider {
        RegistrationViewModel(
            firebaseAuth = instance(),
            dispatcher = Dispatchers.Default
        )
    }

}