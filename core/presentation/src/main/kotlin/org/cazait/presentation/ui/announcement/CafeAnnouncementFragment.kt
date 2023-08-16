package org.cazait.presentation.ui.announcement

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentCafeAnnouncementBinding
import org.cazait.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class CafeAnnouncementFragment : BaseFragment<FragmentCafeAnnouncementBinding>(
    R.layout.fragment_cafe_announcement
){
    private val viewModel: CafeAnnouncementViewModel by viewModels()
}