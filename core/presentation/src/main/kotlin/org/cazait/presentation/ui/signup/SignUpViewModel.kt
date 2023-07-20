package org.cazait.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.DomainError
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

    private val _guideMessageResId = MutableStateFlow<Int?>(null)
    val guideMessageResId = _guideMessageResId.asStateFlow()

    fun signUp() {
        viewModelScope.launch {
            val signUpResult = userUseCase.signUp(
                loginId = emailText.value,
                password = passwordText.value,
                nickname = nicknameText.value
            ).first()

            when (signUpResult) {
                is DomainResult.Success -> {
                    _signUpInfoStateFlow.value = signUpResult.data
                }

                is DomainResult.Error -> {
                }

                is DomainResult.Loading -> {}
            }
        }
    }
}