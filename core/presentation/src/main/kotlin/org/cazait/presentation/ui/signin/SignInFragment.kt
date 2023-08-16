package org.cazait.presentation.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentSignInBinding

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false
        ).apply {
            lifecycleOwner = this@SignInFragment.viewLifecycleOwner
            viewModel = this@SignInFragment.viewModel
            fragment = this@SignInFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userPreference.collect {
                        if (it.isLoggedIn) {
                            navigateToStoreManagedStoresFragment()
                        }
                    }
                }
                launch {
                    viewModel.signInInfoStateFlow.collect { signInResult ->
                        if (signInResult != null)
                            navigateToStoreManagedStoresFragment()
                    }
                }
            }
        }
    }

    private fun navigateToStoreManagedStoresFragment() {
        findNavController().navigate(
            SignInFragmentDirections.actionSignInFragmentToManagedStoresFragment(),
            NavOptions.Builder()
                .setPopUpTo(
                    R.id.signInFragment,
                    true // Inclusive: true if signInFragment should be removed, false otherwise.
                ).build()
        )
    }

    fun navigateToSignUpFragment() {
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }
}