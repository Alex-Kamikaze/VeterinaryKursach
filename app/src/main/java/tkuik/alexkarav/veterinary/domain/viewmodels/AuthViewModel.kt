package tkuik.alexkarav.veterinary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tkuik.alexkarav.veterinary.domain.repository.VeterinaryAppRepositoryImpl
import tkuik.alexkarav.veterinary.ui.components.auth_screen.AuthScreenUIEvents
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: VeterinaryAppRepositoryImpl): ViewModel() {

    private val _authResult = MutableStateFlow<Result<Boolean>?>(null)
    val authResult = _authResult.asStateFlow()


    fun onEvent(uiEvent: AuthScreenUIEvents) {
        when(uiEvent) {
            is AuthScreenUIEvents.OnAuthButtonPressed -> {
                viewModelScope.launch {
                    val result = repo.loginUser(uiEvent.login, uiEvent.password)
                    _authResult.value = result
                }
            }
            is AuthScreenUIEvents.OnRegisterButtonPressed -> {}
        }
    }

    fun clearAuthState() { _authResult.value = null }
}