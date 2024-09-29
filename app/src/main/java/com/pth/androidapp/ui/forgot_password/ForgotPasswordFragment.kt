package com.pth.androidapp.ui.forgot_password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pth.androidapp.R
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.common.Utils.setTextChangeListener
import com.pth.androidapp.databinding.FragmentForgotPasswordBinding
import com.pth.androidapp.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {


    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkIsLogin()) {
            navigateToPage(R.id.action_global_to_main_nav_graph)
        }

        setupDataBinding()
        setupListeners()
        setupObservers()
    }

    private fun setupDataBinding() = with(binding) {
        viewModel = this@ForgotPasswordFragment.viewModel
    }

    private fun setupListeners() = with(binding) {
        emailText.setTextChangeListener(emailLayout)
    }

    private fun setupObservers() {
        viewModel.email.observe(viewLifecycleOwner) { textFieldState ->
            binding.emailLayout.isErrorEnabled = textFieldState.error != null
            binding.emailLayout.error = textFieldState.error
        }

        viewModel.sendForgotPasswordEmailResult.observe(viewLifecycleOwner) {
            val isLoading = it is NetworkResult.Loading
            binding.emailText.isEnabled = !isLoading
            binding.sendEmailButton.isEnabled = !isLoading
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.sendEmailButton.text = if (isLoading) "" else "Send Email"

            when (it) {
                is NetworkResult.Success -> {
                    navigateBack()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}