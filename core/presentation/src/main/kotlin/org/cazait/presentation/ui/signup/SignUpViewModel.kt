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

    val idText = MutableStateFlow("")
    val passwordText = MutableStateFlow("")
    val passwordDoubleCheckText = MutableStateFlow("")
    val nicknameText = MutableStateFlow("")
    val phoneNumberText = MutableStateFlow("")
    val verificationCodeText = MutableStateFlow("")

    private val _signUpInfoStateFlow = MutableStateFlow<SignUpInfo?>(null)
    val signUpInfoStateFlow = _signUpInfoStateFlow.asStateFlow()

    private val _checkPhoneDupStateFlow = MutableStateFlow<String?>(null)
    val checkPhoneDupStateFlow = _checkPhoneDupStateFlow.asStateFlow()

    private val _checkIdDupStateFlow = MutableStateFlow<String?>(null)
    val checkIdDupStateFlow = _checkIdDupStateFlow.asStateFlow()

    private val _checkNicknameDupStateFlow = MutableStateFlow<String?>(null)
    val checkNicknameDupStateFlow = _checkNicknameDupStateFlow.asStateFlow()

    private val _guideMessage = MutableStateFlow("")
    val guideMessage = _guideMessage.asStateFlow()

    fun signUp() {
        viewModelScope.launch {
            userUseCase.signUp(
                loginId = idText.value,
                password = passwordText.value,
                phoneNumber = phoneNumberText.value,
                nickname = nicknameText.value
            ).collect { result ->
                Log.d("SignUpViewModel", result.toString())
                result.onSuccess { signUpInfo ->
                    _signUpInfoStateFlow.value = signUpInfo
                }
            }
        }
    }

    fun checkPhoneDup() {
        viewModelScope.launch {
            userUseCase.checkPhoneDup(phoneNumberText.value, "false").collect { result ->
                Log.d("SignUpViewModel Phone Dup", result.toString())
                result.onSuccess {
                    _checkPhoneDupStateFlow.value = it
                }
            }
        }
    }

    fun checkIdDup() {
        Log.d("SignUpViewModel Clicked?", "adsf")
        viewModelScope.launch {
            userUseCase.checkIdDup(idText.value, "false").collect { result ->
                Log.d("SignUpViewModel Id Dup", result.toString())
                result.onSuccess {
                    _checkIdDupStateFlow.value = it
                }
            }
        }
    }

    fun checkNicknameDup() {
        viewModelScope.launch {
            userUseCase.checkNicknameDup(nicknameText.value, "false").collect { result ->
                Log.d("SignUpViewModel Nickname Dup", result.toString())
                result.onSuccess {
                    _checkNicknameDupStateFlow.value = it
                }
            }
        }
    }

    fun receiveCode() {

    }

    fun sendCode() {

    }

    fun showToastMessage(errorMessage: String?) {
        if (errorMessage == null) return
        _guideMessage.value = errorMessage
    }
}