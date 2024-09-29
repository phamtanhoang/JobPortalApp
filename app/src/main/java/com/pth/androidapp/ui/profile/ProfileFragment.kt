package com.pth.androidapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pth.androidapp.R
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {


        if (!checkIsLogin()) {
            loginButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
            loginButton.setOnClickListener {
                navigateToPage(R.id.action_global_to_auth_nav_graph)
            }
        } else {
            loginButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE
            logoutButton.setOnClickListener {
                viewModel.logout()
            }
        }


    }

    private fun setupObservers() {
        viewModel.logoutSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                navigateToPage(R.id.profileFragment)
            }
        }
    }
}