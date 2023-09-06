package org.bmsk.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.bmsk.domain.model.ManagedCafe

object CacheRepository {
    private val _selectedManagedCafe = MutableStateFlow(ManagedCafe())
    val selectedManagedCafe = _selectedManagedCafe.asStateFlow()

    fun updateSelectedManagedCafe(managedCafe: ManagedCafe) {
        _selectedManagedCafe.update { managedCafe }
    }
}