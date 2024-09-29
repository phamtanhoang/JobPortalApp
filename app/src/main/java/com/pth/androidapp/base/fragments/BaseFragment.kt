package com.pth.androidapp.base.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.pth.androidapp.base.activities.BaseActivity
import com.pth.androidapp.base.dialogs.NotifyType
import com.pth.androidapp.common.TokenManager
import javax.inject.Inject

open class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> VB
) : Fragment() {


    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun checkIsLogin(): Boolean {
        return (requireActivity() as? BaseActivity)?.checkIsLogin() == true
    }

    protected fun navigateBack() = findNavController().popBackStack()

    protected fun navigateToPage(actionId: Int) = findNavController().navigate(actionId)

    protected fun showLoading(isShow: Boolean) {
        (requireActivity() as? BaseActivity)?.showLoading(isShow)
    }

    protected fun showLoadingMore(isShow: Boolean) {

    }

    protected fun showNotify(type: NotifyType, title: String, message: String) {
        (requireActivity() as? BaseActivity)?.showNotifyDialog(type, title, message)
    }
}