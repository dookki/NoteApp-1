package br.com.alaksion.feature_home

import br.com.alaksion.feature_home.presentation.home.HomeScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val homeDi = DI.Module("feature-home") {

    bind<HomeScreenViewModel>() with provider {
        HomeScreenViewModel(
            auth = instance()
        )
    }

}