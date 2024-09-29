package com.pth.androidapp.data.services

import com.pth.androidapp.base.network.BaseRemoteService
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.apis.EmployerApi
import com.pth.androidapp.data.models.employer.Employer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployerRemoteService @Inject constructor(
    private val employerApi: EmployerApi
) : BaseRemoteService() {
    suspend fun getTopEmployers(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<Employer>>> = apiRequestFlow {
        employerApi.getTopEmployers(page, size)
    }
}