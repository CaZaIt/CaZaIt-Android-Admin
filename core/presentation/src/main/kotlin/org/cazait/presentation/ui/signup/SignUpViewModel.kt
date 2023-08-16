package org.cazait.presentation.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val emailText = MutableStateFlow("")
    val passwordText = MutableStateFlow("")
    val passwordDoubleCheckText = MutableStateFlow("")
    val nicknameText = MutableStateFlow("")

    private val _signUpInfoStateFlow = MutableStateFlow<SignUpInfo?>(null)
    val signUpInfoStateFlow = _signUpInfoStateFlow.asStateFlow()

    private val _guideMessage = MutableStateFlow("")
    val guideMessage = _guideMessage.asStateFlow()

    fun signUp() {
        viewModelScope.launch {
            userUseCase.signUp(
                loginId = emailText.value,
                password = passwordText.value,
                nickname = nicknameText.value
            ).collect { result ->
                Log.d("SignUpViewModel", result.toString())
                result.onSuccess { signUpInfo ->
                    _signUpInfoStateFlow.value = signUpInfo
                }
            }
        }
    }
}