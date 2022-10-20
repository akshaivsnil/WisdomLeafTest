package com.akshai.tutorials.wisdomleaftest.network

import com.akshai.tutorials.wisdomleaftest.entity.ListApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ATM on 20/October/2022
 */

interface ApiServices {

    @GET("list")
    suspend fun getListData(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<List<ListApiResponse>>

}