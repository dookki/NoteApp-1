package br.com.alaksion.feature_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal sealed class HomeScreenEvents {
    object Logout : HomeScreenEvents()
}

internal class HomeScreenViewModel(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _events = Channel<HomeScreenEvents>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: HomeIntent) {
        auth.signOut()
        viewModelScope.launch {
            _events.send(HomeScreenEvents.Logout)
        }
    }

}