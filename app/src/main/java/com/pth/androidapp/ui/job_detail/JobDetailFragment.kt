package com.pth.androidapp.ui.job_detail

import android.os.Bundle
import android.view.View
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.databinding.FragmentJobDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailFragment :
    BaseFragment<FragmentJobDetailBinding>(FragmentJobDetailBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {

    }

    private fun setupObservers() {

    }
}