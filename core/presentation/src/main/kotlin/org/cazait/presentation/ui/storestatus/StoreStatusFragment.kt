package org.cazait.presentation.ui.storestatus

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    private lateinit var sideSheetMenu: SideSheetDialog
    private lateinit var sideSheetMenuBinding: LayoutSideSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_store_status,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSideSheetMenu()
        initSideSheetDialog()
    }

    private fun setSideSheetMenu() {
        binding.menuButton.setOnClickListener {
            showSideSheetMenu()
        }
    }

    private fun showSideSheetMenu() {
        sideSheetMenu.show()
    }

    private fun initSideSheetDialog() {
        sideSheetMenu = SideSheetDialog(requireContext())

        sideSheetMenu.behavior.addCallback(object : SideSheetCallback() {
            override fun onStateChanged(sheet: View, newState: Int) {
                if (newState == SideSheetBehavior.STATE_DRAGGING) {
                    sideSheetMenu.behavior.state = SideSheetBehavior.STATE_EXPANDED
                }
            }

            override fun onSlide(sheet: View, slideOffset: Float) {}
        })

        sideSheetMenuBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.layout_side_sheet,
                null,
                false
            )

        sideSheetMenu.setCancelable(false)
        sideSheetMenu.setCanceledOnTouchOutside(true)
        sideSheetMenuBinding.cancelButton.setOnClickListener {
            sideSheetMenu.dismiss()
        }
        sideSheetMenu.setContentView(sideSheetMenuBinding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}