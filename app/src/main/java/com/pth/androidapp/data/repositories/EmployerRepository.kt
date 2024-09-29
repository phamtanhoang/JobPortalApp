package com.pth.androidapp.data.repositories

import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.models.employer.Employer
import kotlinx.coroutines.flow.Flow


interface EmployerRepository {

    suspend fun getTopEmployer(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<Employer>>>

}