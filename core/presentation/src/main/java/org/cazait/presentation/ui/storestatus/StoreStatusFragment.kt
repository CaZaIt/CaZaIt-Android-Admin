package org.cazait.presentation.ui.storestatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentStoreStatusBinding

@AndroidEntryPoint
class StoreStatusFragment : Fragment() {
    private var _binding: FragmentStoreStatusBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StoreStatusViewModel by viewModels()

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

    }
}