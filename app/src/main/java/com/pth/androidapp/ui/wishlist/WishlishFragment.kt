package com.pth.androidapp.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pth.androidapp.R
import com.pth.androidapp.base.fragments.BaseFragment
import com.pth.androidapp.databinding.FragmentRegisterBinding
import com.pth.androidapp.databinding.FragmentWishlishBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlishFragment : BaseFragment<FragmentWishlishBinding>(FragmentWishlishBinding::inflate) {

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