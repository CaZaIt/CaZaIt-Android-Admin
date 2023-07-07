package org.cazait.presentation.ui.storestatus

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.bmsk.domain.usecase.StoreUseCase
import org.cazait.presentation.model.StoreState
import javax.inject.Inject

@HiltViewModel
class StoreStatusViewModel @Inject constructor(
    private val useCase: StoreUseCase
): ViewModel() {
    private val _storeState = MutableStateFlow<StoreState?>(null)
    val storeState = _storeState.asStateFlow()

    fun onMenuItem1Click() {

    }

    fun onMenuItem2Click() {

    }

    fun onMenuItem3Click() {

    }

    fun onMenuItem4Click() {

    }

    fun onMenuItem5Click() {

    }
}