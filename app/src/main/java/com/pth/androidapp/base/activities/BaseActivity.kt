package com.pth.androidapp.base.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.pth.androidapp.base.dialogs.ConfirmDialog
import com.pth.androidapp.base.dialogs.NotifyDialog
import com.pth.androidapp.base.dialogs.NotifyType
import com.pth.androidapp.common.TokenManager
import javax.inject.Inject


open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    open fun checkIsLogin() = tokenManager.getAccessToken() != null

    open fun setupWindowInsets(binding: ViewBinding) {
        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    open fun navigateToActivity(
        activityClass: Class<out Activity>,
        extras: Bundle? = null,
        finishCurrent: Boolean = true
    ) {
        val intent = Intent(this, activityClass)
        extras?.let { intent.putExtras(it) }
        startActivity(intent)
        if (finishCurrent) finish()
    }

    open fun showLoading(isShow: Boolean) {

    }

    open fun showNotifyDialog(
        type: NotifyType,
        message: String,
        title: String,
        textButton: String? = null
    ) {
        val notifyDialog = NotifyDialog(
            type = type,
            context = this,
            title = title,
            message = message,
            textButton = textButton
        )
        notifyDialog.show()
        notifyDialog.window?.setGravity(Gravity.CENTER)
        notifyDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    open fun showConfirmDialog(
        title: String,
        message: String?,
        positiveButtonTitle: String,
        negativeButtonTitle: String,
        textButton: String?,
        callback: ConfirmDialog.ConfirmCallback
    ) {
        val confirmDialog = ConfirmDialog(
            context = this,
            title = title,
            message = message,
            positiveButtonTitle = positiveButtonTitle,
            negativeButtonTitle = negativeButtonTitle,
            callback = callback
        )
        confirmDialog.show()
        confirmDialog.window?.setGravity(Gravity.CENTER)
        confirmDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}