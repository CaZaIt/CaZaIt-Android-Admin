package org.cazait.ui.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import org.cazait.R
import SignInFormState
import SignInRepository
import SignInResult
import model.Result

class LoginViewModel(private val loginRepository: SignInRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<SignInFormState>()
    val loginFormState: LiveData<SignInFormState> = _loginForm

    private val _loginResult = MutableLiveData<SignInResult>()
    val loginResult: LiveData<SignInResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.signIn(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                SignInResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = SignInResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = SignInFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = SignInFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = SignInFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}