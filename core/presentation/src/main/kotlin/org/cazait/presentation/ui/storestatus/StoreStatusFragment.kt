package org.cazait.presentation.ui.storestatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.sidesheet.SideSheetBehavior
import com.google.android.material.sidesheet.SideSheetCallback
import com.google.android.material.sidesheet.SideSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.bmsk.domain.exception.DomainError
import org.bmsk.domain.exception.ErrorType
import org.bmsk.domain.exception.UnauthorizedException
import org.bmsk.domain.model.ManagedCafe
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentStoreStatusBinding
import org.cazait.presentation.databinding.LayoutSideSheetBinding
import java.io.IOException

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
        _binding = DataBindingUtil.inflate<FragmentStoreStatusBinding>(
            inflater,
            R.layout.fragment_store_status,
            container,
            false
        ).apply {
            fragment = this@StoreStatusFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    fun showMenu() {
        binding.menuButton.setOnClickListener { sideSheetMenu.show() }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.errorEventFlow.collectLatest {
                    handleError(it)
                }
            }
            launch {
                viewModel.selectedCafeFlow.collectLatest {
                    it?.let { updateView(it) }
                }
            }

            viewModel.fetchManagedCafes()
        }
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
        storeMenuSettingButton.setOnClickListener {
            sideSheetMenu.cancel()
            navigateToCafeMenuSettingFragment()
        }
    }

    private fun navigateToCafeImageSettingFragment() {
        findNavController().navigate(StoreStatusFragmentDirections.actionStoreStatusFragmentToCafeImageSettingFragment())
    }

    private fun navigateToCafeMenuSettingFragment() {
        findNavController().navigate(StoreStatusFragmentDirections.actionStoreStatusFragmentToCafeMenuSettingFragment())
    }

    private fun handleError(exception: Exception) {
        val message = when (exception) {
            is IOException -> getString(R.string.network_error_occurred)
            is UnauthorizedException -> getString(R.string.authorization_error_occurred)
            else -> ""
        }
        showToast(message)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateView(managedCafe: ManagedCafe) {
        binding.tvCafeName.text = managedCafe.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}