package com.pth.androidapp.data.services

import com.pth.androidapp.base.network.BaseRemoteService
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.apis.CategoryApi
import com.pth.androidapp.data.models.category.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRemoteService @Inject constructor(
    private val categoryApi: CategoryApi
) : BaseRemoteService() {
    suspend fun getTopCategories(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<Category>>> = apiRequestFlow {
        categoryApi.getTopCategories(page, size)
    }
}