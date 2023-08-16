package org.cazait.presentation.ui.storestatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.bmsk.domain.model.ManagedCafe
import org.bmsk.domain.usecase.StoreUseCase
import org.cazait.presentation.model.StoreState
import javax.inject.Inject

@HiltViewModel
class StoreStatusViewModel @Inject constructor(
    private val useCase: StoreUseCase
) : ViewModel() {
    private val _storeState = MutableStateFlow<StoreState?>(null)
    val storeState = _storeState.asStateFlow()

    private val _selectedCafeFlow = MutableStateFlow<ManagedCafe?>(null)
    val selectedCafeFlow = _selectedCafeFlow.asStateFlow()

    private val _managedCafesFlow = MutableStateFlow<List<ManagedCafe>>(emptyList())
    val managedCafesFlow = _managedCafesFlow.asStateFlow()

    private val _errorEventFlow = MutableSharedFlow<Exception>()
    val errorEventFlow = _errorEventFlow.asSharedFlow()

    fun fetchManagedCafes() {
        viewModelScope.launch {
            useCase.getManagedCafes().collect {
                it.onSuccess { list ->
                    _managedCafesFlow.value = list
                }
            }
        }
    }

    fun selectManagedCafe(managedCafe: ManagedCafe) {
        _selectedCafeFlow.value = managedCafe
    }
}