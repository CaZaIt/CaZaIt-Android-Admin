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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.cazait.presentation.R
import org.cazait.presentation.databinding.FragmentSignUpBinding
import org.cazait.presentation.ui.util.toGone
import org.cazait.presentation.ui.util.toVisible

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
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutVerificationCodeInput.toGone()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.signUpInfoStateFlow.collect {
                        if (it != null) {
                            navigateToBackStack()
                        }
                    }
                }
                launch {
                    viewModel.checkPhoneDupStateFlow.collect {
                        binding.layoutVerificationCodeInput.toVisible()
                        viewModel.showToastMessage(it)
                        viewModel.receiveCode()
                    }
                }
                launch {
                    viewModel.checkIdDupStateFlow.collect {
                        viewModel.showToastMessage(it)
                    }
                }
                launch {
                    viewModel.checkNicknameDupStateFlow.collect {
                        viewModel.showToastMessage(it)
                    }
                }
            }
        }
    }

    private fun navigateToBackStack() {
        findNavController().popBackStack()
    }
}