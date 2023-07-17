package org.cazait.presentation.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.bmsk.domain.DomainResult
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

    private val _guideMessage = MutableStateFlow("")
    val guideMessage = _guideMessage.asStateFlow()

    init {
        viewModelScope.launch {
            _userPreference.value = userUseCase.getCurrentUser().first()
        }
    }

    fun signIn() {
        Log.d("SignInViewModel", "singin")
        viewModelScope.launch {
            val signInResult = userUseCase.signIn(emailText.value, passwordText.value).first()
            if (signInResult is DomainResult.Success) {
                _signInInfoStateFlow.value = signInResult.data
            } else if (signInResult is DomainResult.Error) {
                // 대응되는 코드 어떻게 할지 고민
            } else {
                Log.d("SignInViewModel", "뭐가 문제야.")
            }
        }
    }
}