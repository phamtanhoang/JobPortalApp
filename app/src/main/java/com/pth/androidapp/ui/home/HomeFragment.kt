package com.pth.androidapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.pth.androidapp.R
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDataBinding()
        setupListeners()
        setupObservers()

    }

    private fun setupDataBinding() = with(binding) {
        viewModel = this@HomeFragment.viewModel
    }

    private fun setupObservers() {
        viewModel.fetchPageData()
    }

    private fun setupListeners() = with(binding) {

    }

}