package com.pth.androidapp.data.repositories.impl

import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.common.TokenManager
import com.pth.androidapp.data.models.employer.Employer
import com.pth.androidapp.data.repositories.EmployerRepository
import com.pth.androidapp.data.services.EmployerRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployerRepositoryImpl @Inject constructor(
    private val employerRemoteService: EmployerRemoteService,
    private val tokenManager: TokenManager
) : EmployerRepository {

    override suspend fun getTopEmployer(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<Employer>>> {
        return employerRemoteService.getTopEmployers(page, size)
    }

}