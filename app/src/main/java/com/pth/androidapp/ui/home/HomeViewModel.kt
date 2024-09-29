package com.pth.androidapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.base.viewmodels.BaseViewModel
import com.pth.androidapp.data.models.PageableRequest
import com.pth.androidapp.data.models.category.Category
import com.pth.androidapp.data.models.employer.Employer
import com.pth.androidapp.data.models.job.JobInHome1
import com.pth.androidapp.data.models.job.JobInHome2
import com.pth.androidapp.data.repositories.CategoryRepository
import com.pth.androidapp.data.repositories.EmployerRepository
import com.pth.androidapp.data.repositories.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jobRepository: JobRepository,
    private val categoryRepository: CategoryRepository,
    private val employerRepository: EmployerRepository
) : BaseViewModel() {



    private val _newJobPageable = MutableLiveData(PageableRequest())
    val newJobPageable: LiveData<PageableRequest> = _newJobPageable

    private val _topJobPageable = MutableLiveData(PageableRequest())
    val topJobPageable: LiveData<PageableRequest> = _topJobPageable

    private val _topCategoryPageable = MutableLiveData(PageableRequest())
    val topCategoryPageable: LiveData<PageableRequest> = _topCategoryPageable

    private val _topEmployerPageable = MutableLiveData(PageableRequest())
    val topEmployerPageable: LiveData<PageableRequest> = _topEmployerPageable


    private val _newJobResult =
        MutableLiveData<NetworkResult<ListingsResponse<JobInHome2>>>(NetworkResult.Idle)
    val newJobResult: LiveData<NetworkResult<ListingsResponse<JobInHome2>>> = _newJobResult

    private val _topJobResult =
        MutableLiveData<NetworkResult<ListingsResponse<JobInHome1>>>(NetworkResult.Idle)
    val topJobResult: LiveData<NetworkResult<ListingsResponse<JobInHome1>>> = _topJobResult

    private val _topCategoryResult =
        MutableLiveData<NetworkResult<ListingsResponse<Category>>>(NetworkResult.Idle)
    val topCategoryResult: LiveData<NetworkResult<ListingsResponse<Category>>> = _topCategoryResult

    private val _topEmployerResult =
        MutableLiveData<NetworkResult<ListingsResponse<Employer>>>(NetworkResult.Idle)
    val topEmployerResult: LiveData<NetworkResult<ListingsResponse<Employer>>> = _topEmployerResult

    fun onNewJobPageableChange(page: Int, size: Int) {
        _newJobPageable.value = PageableRequest(page, size)
    }

    fun onTopJobPageableChange(page: Int, size: Int) {
        _topJobPageable.value = PageableRequest(page, size)
    }

    fun onTopCategoryPageableChange(page: Int, size: Int) {
        _topCategoryPageable.value = PageableRequest(page, size)
    }

    fun onTopEmployerPageableChange(page: Int, size: Int) {
        _topEmployerPageable.value = PageableRequest(page, size)
    }


    override fun fetchPageData() {
        getNewJob()
        getTopJob()
        getTopCategory()
        getTopEmployer()
    }

    fun getNewJob() {
        viewModelScope.launch {
            val pageable = newJobPageable.value ?: PageableRequest()
            jobRepository.getNewJob(pageable.page, pageable.size).collect {
                _newJobResult.value = it
                Log.d("HomeViewModel", "getNewJob: $it")
            }
        }
    }

    fun getTopJob() {
        viewModelScope.launch {
            val pageable = topJobPageable.value ?: PageableRequest()
            jobRepository.getTopJob(pageable.page, pageable.size).collect {
                _topJobResult.value = it
                Log.d("HomeViewModel", "getTopJob: $it")

            }
        }
    }

    fun getTopCategory() {
        viewModelScope.launch {
            val pageable = topCategoryPageable.value ?: PageableRequest()
            categoryRepository.getTopCategories(pageable.page, pageable.size).collect {
                _topCategoryResult.value = it
                Log.d("HomeViewModel", "getTopCategory: $it")
            }
        }
    }

    fun getTopEmployer() {
        viewModelScope.launch {
            val pageable = topEmployerPageable.value ?: PageableRequest()
            employerRepository.getTopEmployer(pageable.page, pageable.size).collect {
                _topEmployerResult.value = it
                Log.d("HomeViewModel", "getTopEmployer: $it")
            }
        }
    }

}