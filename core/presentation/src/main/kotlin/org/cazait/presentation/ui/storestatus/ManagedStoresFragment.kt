package org.cazait.presentation.ui.storestatus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.bmsk.domain.exception.UnauthorizedException
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentManagedStoresBinding
import org.cazait.presentation.ui.adapter.ManagedCafesAdapter

@AndroidEntryPoint
class ManagedStoresFragment : Fragment() {
    private val viewModel: StoreStatusViewModel by viewModels()

    private var _binding: FragmentManagedStoresBinding? = null
    private val binding get() = _binding!!

    private val managedCafesAdapter: ManagedCafesAdapter by lazy {
        createManagedCafesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_managed_stores,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.managedCafesRecyclerView.adapter = managedCafesAdapter
        binding.managedCafesRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun observeViewModel() {
        viewModel.fetchManagedCafes()

        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.managedCafesFlow.collectLatest {
                    managedCafesAdapter.submitList(it)
                }
            }
            launch {
                viewModel.errorEventFlow.collect {
                    Log.e("ManagedStoresFragment", it.toString())
                    if (it is UnauthorizedException) {
                        Log.e("ManagedStoresFragment", it.toString())

                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun createManagedCafesAdapter() = ManagedCafesAdapter(
        onClick = { managedCafe ->
            viewModel.selectManagedCafe(managedCafe)
            navigateToStoreStatusFragment()
        }
    )

    private fun navigateToStoreStatusFragment() {
        findNavController().navigate(ManagedStoresFragmentDirections.actionManagedStoresFragmentToStoreStatusFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}