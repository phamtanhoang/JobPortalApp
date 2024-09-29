package com.pth.androidapp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pth.androidapp.R
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.common.Utils.setTextChangeListener
import com.pth.androidapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

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
        viewModel = this@LoginFragment.viewModel
    }

    private fun setupListeners() = with(binding) {
        signupButton.setOnClickListener {
            navigateToPage(R.id.action_loginFragment_to_registerFragment)
        }
        forgotPasswordButton.setOnClickListener {
            navigateToPage(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        emailText.setTextChangeListener(emailLayout)
        passwordText.setTextChangeListener(passwordLayout)
    }

    private fun setupObservers() {
        viewModel.email.observe(viewLifecycleOwner) { textFieldState ->
            binding.emailLayout.isErrorEnabled = textFieldState.error != null
            binding.emailLayout.error = textFieldState.error
        }
        viewModel.password.observe(viewLifecycleOwner) { textFieldState ->
            binding.passwordLayout.isErrorEnabled = textFieldState.error != null
            binding.passwordLayout.error = textFieldState.error
        }

        viewModel.rememberMe.observe(viewLifecycleOwner) {
            binding.rememberCheckbox.setImageResource(
                if (it) R.drawable.ic_check_circle else R.drawable.ic_radio_button_unchecked
            )
            binding.rememberCheckbox.setColorFilter(
                resources.getColor(if (it) R.color.primaryDarkColor else R.color.text_color)
            )
        }

        viewModel.loginResult.observe(viewLifecycleOwner) {
            val isLoading = it is NetworkResult.Loading
            binding.emailText.isEnabled = !isLoading
            binding.passwordText.isEnabled = !isLoading
            binding.loginButton.isEnabled = !isLoading
            binding.signupButton.isEnabled = !isLoading
            binding.rememberCheckbox.isEnabled = !isLoading
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.loginButton.text = if (isLoading) "" else "Login"

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