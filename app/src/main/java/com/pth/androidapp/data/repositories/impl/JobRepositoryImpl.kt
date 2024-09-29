package com.pth.androidapp.data.repositories.impl

import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.common.TokenManager
import com.pth.androidapp.data.models.job.JobInHome1
import com.pth.androidapp.data.models.job.JobInHome2
import com.pth.androidapp.data.repositories.JobRepository
import com.pth.androidapp.data.services.JobRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val jobRemoteService: JobRemoteService,
    private val tokenManager: TokenManager
) : JobRepository {
    override suspend fun getNewJob(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<JobInHome2>>> {
        return jobRemoteService.getNewJob(page, size)
    }

    override suspend fun getTopJob(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<JobInHome1>>> {
        return jobRemoteService.getTopJob(page, size)
    }

}