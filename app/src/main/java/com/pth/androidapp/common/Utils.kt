package com.pth.androidapp.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

object Utils {

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getEmailErrorMessage(email: String): String? {
        if (email.isEmpty()) {
            return "Email is required!"
        } else if (!isEmailValid(email)) {
            return "Invalid email!"
        }
        return null
    }

    fun validatePassword(password: String): Boolean {
        if (password.isEmpty()) {
            return false
        }
        if (password.length < 8) {
            return false
        }
        if (!password.contains(Regex("[A-Z]"))) {
            return false
        }
        if (!password.contains(Regex("[a-z]"))) {
            return false
        }
        if (!password.contains(Regex("[0-9]"))) {
            return false
        }
        if (password.matches(Regex("^[a-zA-Z0-9]+\$"))) {
            return false
        }
        return true
    }

    fun getPasswordErrorMessage(password: String): String? {
        if (password.isEmpty()) {
            return "Password is required!"
        }
        if (password.length < 8) {
            return "Password must be at least 8 characters long!"
        }
        if (!password.contains(Regex("[A-Z]"))) {
            return "Password must contain at least one uppercase character!"
        }
        if (!password.contains(Regex("[a-z]"))) {
            return "Password must contain at least one lowercase character!"
        }
        if (!password.contains(Regex("[0-9]"))) {
            return "Password must contain at least one digit character!"
        }
        if (password.matches(Regex("^[a-zA-Z0-9]+\$"))) {
            return "Password must contain at least one special character!"
        }
        return null
    }


    fun hideSoftKeyboard(view: View, context: Context) {
        val imm: InputMethodManager? =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun EditText.setTextChangeListener(textInputLayout: TextInputLayout) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textInputLayout.isErrorEnabled = false
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

}