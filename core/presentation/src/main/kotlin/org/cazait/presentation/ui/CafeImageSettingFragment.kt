package org.cazait.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentCafeImageSettingBinding
import org.cazait.presentation.ui.base.BaseFragment

class CafeImageSettingFragment : BaseFragment<FragmentCafeImageSettingBinding>(
    R.layout.fragment_cafe_image_setting
) {
    private val viewModel: ImageSettingViewModel by viewModels()
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.updateSelectedUri(uri)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.fragment = this
    }

    private fun setupViewModel() {

    }

    fun startPicker() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}