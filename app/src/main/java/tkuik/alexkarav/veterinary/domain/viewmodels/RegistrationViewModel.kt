package tkuik.alexkarav.veterinary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tkuik.alexkarav.veterinary.data.models.auth.UserRegistrationData
import tkuik.alexkarav.veterinary.domain.repository.VeterinaryAppRepositoryImpl
import tkuik.alexkarav.veterinary.ui.components.registration_screen.RegistrationScreenUIEvents
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: VeterinaryAppRepositoryImpl): ViewModel() {

    private val _registrationState = MutableStateFlow<Result<Boolean>?>(null)
    val registrationState = _registrationState.asStateFlow()

    fun onEvent(uiEvents: RegistrationScreenUIEvents) {
        when(uiEvents) {
            is RegistrationScreenUIEvents.OnRegisterButtonPressed -> registerUser(uiEvents.userName, uiEvents.userSurname, uiEvents.userEmail, uiEvents.userPhone, uiEvents.userPassword)
        }
    }

    private fun registerUser(userName: String, userSurname: String, userEmail: String, userPhone: String, userPassword: String) {
        viewModelScope.launch {
            val result = repository.registerUser(
                UserRegistrationData(
                userName = userName,
                userSurname = userSurname,
                userEmail = userEmail,
                userPassword = userPassword,
                userPhone = userPhone)
            )
            _registrationState.value = result
        }
    }

    fun clearState() {
        _registrationState.value = null
    }

    fun onRegistrationComplete() {
        clearState()
    }
}