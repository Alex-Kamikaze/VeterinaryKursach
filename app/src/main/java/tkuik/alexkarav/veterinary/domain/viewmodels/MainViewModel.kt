package tkuik.alexkarav.veterinary.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tkuik.alexkarav.veterinary.domain.repository.VeterinaryAppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: VeterinaryAppRepositoryImpl): ViewModel() {

    private val _introScreenCompleted = MutableStateFlow<Boolean?>(true)
    val introScreenCompleted = _introScreenCompleted.asStateFlow()

    private val _showSplash = MutableStateFlow(true)
    val showSplash = _showSplash.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getIntroScreenCompletion().collectLatest {
                _introScreenCompleted.value = it
                _showSplash.value = false
            }
        }

    }

    fun setIntroScreenCompletion() {
        viewModelScope.launch {
            repo.setIntroScreenCompletion(true)
        }
    }
}