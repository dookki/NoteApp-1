package br.com.alaksion.utils.injection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import org.kodein.di.compose.LocalDI
import org.kodein.di.instance

@Composable
inline fun <reified T : ViewModel> rememberViewModel(): T {
    val di = LocalDI.current
    val viewModel by di.instance<T>()

    return remember { viewModel }
}