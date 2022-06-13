package br.com.alaksion.feature_login.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal sealed class RegistrationEvents {
    data class Error(val message: String) : RegistrationEvents()
    object NavigateToHome : RegistrationEvents()
}

internal class RegistrationViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val mutableState = MutableStateFlow(RegistrationState())
    val state = mutableState.asStateFlow()

    private val eventChannel = Channel<RegistrationEvents>()
    val events = eventChannel.receiveAsFlow()

    fun updateEmail(value: String) {
        mutableState.update {
            it.copy(email = value)
        }
        setButtonEnabled()
    }

    fun updatePassword(value: String) {
        mutableState.update {
            it.copy(password = value)
        }
        setButtonEnabled()
    }

    fun updateConfirmEmail(value: String) {
        mutableState.update {
            it.copy(confirmEmail = value)
        }
        setButtonEnabled()
    }

    fun toggleShowPassword() {
        mutableState.update {
            it.copy(
                passwordVisibility = it.passwordVisibility.toggle()
            )
        }
    }

    private fun setButtonEnabled() {
        mutableState.update {
            val areFieldsFilled = it.email.isNotEmpty() && it.password.isNotEmpty()
                    && it.confirmEmail.isNotEmpty()
            val isEmailEqual = it.email == it.confirmEmail

            it.copy(
                isButtonEnabled = areFieldsFilled && isEmailEqual
            )
        }
    }

    fun createAccount() {
        mutableState.update {
            it.copy(isButtonLoading = true)
        }
        mutableState.value.let { state ->
            firebaseAuth.createUserWithEmailAndPassword(state.email, state.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        emitEvent(RegistrationEvents.NavigateToHome)
                    } else {
                        emitEvent(RegistrationEvents.Error(task.exception?.message.orEmpty()))
                    }
                }
        }
        mutableState.update {
            it.copy(isButtonLoading = false)
        }
    }

    private fun emitEvent(event: RegistrationEvents) {
        viewModelScope.launch(dispatcher) {
            eventChannel.send(event)
        }
    }

}