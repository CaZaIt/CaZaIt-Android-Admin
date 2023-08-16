package org.cazait.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.bmsk.domain.DomainResult
import org.bmsk.domain.model.CafeMenu
import org.bmsk.domain.usecase.ErrorUseCase
import org.bmsk.domain.usecase.StoreUseCase
import javax.inject.Inject

@HiltViewModel
class CafeMenuSettingViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
    private val errorUseCase: ErrorUseCase
) : ViewModel() {
    private val _submitMenuChangesEvent = MutableSharedFlow<DomainResult<String>>()
    val submitMenuChangesEvent = _submitMenuChangesEvent.asSharedFlow()

    val selectedMenuFlow = MutableStateFlow<CafeMenu?>(null)

    fun submitMenuChanges(
        changedName: String,
        changedDescription: String,
        changedPrice: Int,
        changedImageUrl: String
    ) {
        selectedMenuFlow.value?.let {
            viewModelScope.launch {

            }
        }
    }
}