package org.cazait.presentation.ui.description

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentCafeDescriptionBinding
import org.cazait.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class CafeDescriptionFragment: BaseFragment<FragmentCafeDescriptionBinding>(
    R.layout.fragment_cafe_description
) {
    private val viewModel:CafeDescriptionViewModel by viewModels()

}