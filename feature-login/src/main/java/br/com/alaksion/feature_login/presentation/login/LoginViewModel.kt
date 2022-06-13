package br.com.alaksion.feature_login.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alaksion.feature_login.presentation.login.models.LoginState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal sealed class LoginEvents {
    object LoginSuccess : LoginEvents()
    data class LoginError(val message: String) : LoginEvents()
}

internal class LoginViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val eventChannel = Channel<LoginEvents>()
    val events = eventChannel.receiveAsFlow()

    private val mutableState = MutableStateFlow(LoginState())
    val state = mutableState.asStateFlow()

    fun onChangeEmail(value: String) {
        mutableState.update {
            it.copy(email = value)
        }
        checkButtonEnabled()
    }

    fun onChangePassword(value: String) {
        mutableState.update {
            it.copy(password = value)
        }
        checkButtonEnabled()
    }

    private fun checkButtonEnabled() {
        mutableState.update {
            it.copy(isButtonEnabled = it.email.isNotBlank() && it.password.isNotBlank())
        }
    }

    fun togglePasswordVisibility() {
        mutableState.update {
            it.copy(
                passwordVisibility = it.passwordVisibility.toggle()
            )
        }
    }

    fun submitLogin() {
        val currentState = mutableState.value

        mutableState.update { state -> state.copy(isButtonLoading = true) }

        if (currentState.email.isNotBlank() && currentState.password.isNotBlank()) {
            firebaseAuth.signInWithEmailAndPassword(
                currentState.email,
                currentState.password
            ).addOnCompleteListener {
                mutableState.update { state -> state.copy(isButtonLoading = false) }
                handleLoginResult(it)
            }
        }
    }

    private fun handleLoginResult(result: Task<AuthResult>) {
        viewModelScope.launch(dispatcher) {
            if (result.isSuccessful) {
                eventChannel.send(LoginEvents.LoginSuccess)
            } else {
                eventChannel.send(
                    LoginEvents.LoginError(result.exception?.localizedMessage.orEmpty())
                )
            }
        }
    }

    fun handleSocialLogin(result: GoogleSignInAccount?) {
        viewModelScope.launch(dispatcher) {
            result?.run {
                eventChannel.send(LoginEvents.LoginSuccess)
            }
        }
    }

}