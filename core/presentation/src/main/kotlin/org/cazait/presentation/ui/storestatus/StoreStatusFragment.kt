package org.cazait.presentation.ui.storestatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.sidesheet.SideSheetBehavior
import com.google.android.material.sidesheet.SideSheetCallback
import com.google.android.material.sidesheet.SideSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentStoreStatusBinding
import org.cazait.presentation.databinding.LayoutSideSheetBinding

@AndroidEntryPoint
class StoreStatusFragment : Fragment() {
    private var _binding: FragmentStoreStatusBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StoreStatusViewModel by activityViewModels()
    private val sideSheetMenu: SideSheetDialog by lazy { createSideSheetDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_store_status, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenuButton()
    }

    private fun initMenuButton() {
        binding.menuButton.setOnClickListener { sideSheetMenu.show() }
    }

    private fun createSideSheetDialog() = SideSheetDialog(requireContext()).apply {
        behavior.addCallback(createSideSheetCallback())
        setCancelable(false)
        setCanceledOnTouchOutside(true)
        setContentView(createSideSheetBinding().root)
    }

    private fun createSideSheetCallback() = object : SideSheetCallback() {
        override fun onStateChanged(sheet: View, newState: Int) {
            if (newState == SideSheetBehavior.STATE_DRAGGING) {
                sideSheetMenu.behavior.state = SideSheetBehavior.STATE_EXPANDED
            }
        }

        override fun onSlide(sheet: View, slideOffset: Float) = Unit
    }

    private fun createSideSheetBinding() = DataBindingUtil.inflate<LayoutSideSheetBinding>(
        layoutInflater,
        R.layout.layout_side_sheet,
        null,
        false
    ).apply {
        cancelButton.setOnClickListener { sideSheetMenu.cancel() }
        storePictureSettingButton.setOnClickListener {
            sideSheetMenu.cancel()
            navigateToCafeImageSettingFragment()
        }
    }

    private fun navigateToCafeImageSettingFragment() {
        findNavController().navigate(StoreStatusFragmentDirections.actionStoreStatusFragmentToCafeImageSettingFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}