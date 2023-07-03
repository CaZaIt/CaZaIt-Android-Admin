package org.cazait.presentation.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.bmsk.domain.model.SignInResult
import org.bmsk.domain.usecase.UserUseCase
import org.cazait.model.local.UserPreference
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {
    private val _userPreference = MutableStateFlow(UserPreference.getDefaultInstance())
    val userPreference = _userPreference.asStateFlow()

    val emailText = MutableStateFlow("")
    val passwordText = MutableStateFlow("")

    private val _signInResult = MutableStateFlow<SignInResult?>(null)
    val signInResult = _signInResult.asStateFlow()

    init {
        viewModelScope.launch {
            _userPreference.value = userUseCase.getCurrentUser().first()
        }
    }

    fun signIn() {
        viewModelScope.launch {
            _signInResult.value = userUseCase.signIn(emailText.value, passwordText.value).first()
        }
    }
}