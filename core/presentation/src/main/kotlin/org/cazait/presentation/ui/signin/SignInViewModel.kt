package org.cazait.presentation.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.usecase.UserUseCase
import org.cazait.model.local.UserPreference
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _userPreference = MutableStateFlow(UserPreference.getDefaultInstance())
    val userPreference = _userPreference.asStateFlow()

    val emailText = MutableStateFlow("")
    val passwordText = MutableStateFlow("")

    private val _signInInfoStateFlow = MutableStateFlow<SignInInfo?>(null)
    val signInInfoStateFlow = _signInInfoStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _userPreference.value = userUseCase.getCurrentUser()
        }
    }

    fun signIn() {
        Log.d("SignInViewModel", "singin")
        viewModelScope.launch {
            userUseCase.signIn(emailText.value, passwordText.value).collect { result ->
                Log.d("SignInViewModel", result.toString())
                result.onSuccess { signInInfo ->
                    _signInInfoStateFlow.value = signInInfo
                    userUseCase.saveUserSignInformation(signInInfo)
                    _userPreference.value = userUseCase.getCurrentUser()
                }.onFailure {
                    Log.e("SignInViewModel", "실패입니다.")
                }
            }
        }
    }
}