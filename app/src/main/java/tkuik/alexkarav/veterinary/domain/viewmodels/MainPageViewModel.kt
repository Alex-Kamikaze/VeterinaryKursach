package tkuik.alexkarav.veterinary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tkuik.alexkarav.veterinary.domain.repository.VeterinaryAppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(private val repositoryImpl: VeterinaryAppRepositoryImpl): ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    fun getUsername() {
        viewModelScope.launch {
            repositoryImpl.getUsername().collect {
                _username.value = it
            }
        }
    }
}