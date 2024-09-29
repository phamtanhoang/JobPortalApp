package com.pth.androidapp.data.repositories.impl

import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.common.TokenManager
import com.pth.androidapp.data.models.category.Category
import com.pth.androidapp.data.repositories.CategoryRepository
import com.pth.androidapp.data.services.CategoryRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteService: CategoryRemoteService,
    private val tokenManager: TokenManager
) : CategoryRepository {

    override suspend fun getTopCategories(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<Category>>> {
        return categoryRemoteService.getTopCategories(page, size)
    }



}