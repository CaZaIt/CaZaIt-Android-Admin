package org.cazait.presentation.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.bmsk.domain.usecase.StoreUseCase
import javax.inject.Inject

@HiltViewModel
class CafeMenuSettingViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase
): ViewModel() {

}