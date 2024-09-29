package com.pth.androidapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pth.androidapp.R
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.databinding.FragmentRegisterBinding
import com.pth.androidapp.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

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
        viewModel = this@RegisterFragment.viewModel
    }

    private fun setupListeners() = with(binding) {

    }

    private fun setupObservers() {
        viewModel.agreePolicy.observe(viewLifecycleOwner) {
            if(it){
                binding.agreeCheckbox.setImageResource(R.drawable.ic_check_circle)
                binding.agreeCheckbox.setColorFilter(resources.getColor(R.color.primaryDarkColor))
            }else{
                binding.agreeCheckbox.setImageResource(R.drawable.ic_radio_button_unchecked)
                binding.agreeCheckbox.setColorFilter(resources.getColor(R.color.text_color))
            }
        }
    }
}