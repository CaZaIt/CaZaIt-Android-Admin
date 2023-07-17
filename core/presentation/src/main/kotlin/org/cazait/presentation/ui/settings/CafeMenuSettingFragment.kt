package org.cazait.presentation.ui.settings

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentCafeMenuSettingBinding
import org.cazait.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class CafeMenuSettingFragment : BaseFragment<FragmentCafeMenuSettingBinding>(
    R.layout.fragment_cafe_menu_setting
) {
    private val viewModel: CafeMenuSettingViewModel by viewModels()

}