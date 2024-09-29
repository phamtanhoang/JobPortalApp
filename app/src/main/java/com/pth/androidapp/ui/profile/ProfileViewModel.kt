package com.pth.androidapp.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pth.androidapp.base.viewmodels.BaseViewModel
import com.pth.androidapp.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {
    val logoutSuccess = MutableLiveData<Boolean>()

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            logoutSuccess.value = true
        }
    }
}