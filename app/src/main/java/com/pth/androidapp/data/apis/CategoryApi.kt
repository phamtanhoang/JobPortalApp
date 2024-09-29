package com.pth.androidapp.data.apis

import com.pth.androidapp.base.network.BaseResponse
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.data.models.category.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {
    @GET("categories/topCategories")
    suspend fun getTopCategories(
        @Query("page") page: Number,
        @Query("size") size: Number
    ): Response<BaseResponse<ListingsResponse<Category>>>

}