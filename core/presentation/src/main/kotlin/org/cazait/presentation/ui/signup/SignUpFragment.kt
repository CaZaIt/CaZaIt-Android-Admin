package org.cazait.presentation.ui.signup

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
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentSignUpBinding

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val viewModel: SignUpViewModel by activityViewModels()
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate<FragmentSignUpBinding?>(
                inflater,
                R.layout.fragment_sign_up,
                container,
                false
            ).apply {
                lifecycleOwner = this@SignUpFragment.viewLifecycleOwner
                viewModel = this@SignUpFragment.viewModel
                fragment = this@SignUpFragment
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.signUpInfoStateFlow.collect {
                        if (it != null) {
                            navigateToBackStack()
                        }
                    }
                }
                launch {
                    viewModel.guideMessage.collect { message ->
                        showMessage(message)
                    }
                }
            }
        }
    }

    private fun navigateToBackStack() {
        findNavController().popBackStack()
    }

    private fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}