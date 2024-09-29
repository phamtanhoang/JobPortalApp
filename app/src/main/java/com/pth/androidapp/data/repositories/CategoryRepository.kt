package com.pth.androidapp.data.repositories

import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.models.category.Category
import kotlinx.coroutines.flow.Flow


interface CategoryRepository {

    suspend fun getTopCategories(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<Category>>>

}