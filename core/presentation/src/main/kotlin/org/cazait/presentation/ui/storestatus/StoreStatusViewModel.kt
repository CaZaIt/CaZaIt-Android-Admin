package org.cazait.presentation.ui.storestatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.DomainError
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

    private val managedCafes = mutableSetOf<ManagedCafe>()
    private val _managedCafesFlow = MutableSharedFlow<List<ManagedCafe>>()
    val managedCafesFlow = _managedCafesFlow.asSharedFlow()

    private val _errorEventFlow = MutableSharedFlow<DomainError>()
    val errorEventFlow = _errorEventFlow.asSharedFlow()

    fun fetchManagedCafes() {
        viewModelScope.launch {
            val fetchResult = useCase.getManagedCafes().first()
            if (fetchResult is DomainResult.Error) {
                _errorEventFlow.emit(fetchResult.error)
            } else if (fetchResult is DomainResult.Success) {
                managedCafes.clear()
                managedCafes.addAll(fetchResult.data)

                _managedCafesFlow.emit(managedCafes.toList())
            }
        }
    }

    fun selectManagedCafe(managedCafe: ManagedCafe) {
        _selectedCafeFlow.value = managedCafe
    }
}