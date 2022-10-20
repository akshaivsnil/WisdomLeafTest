package com.akshai.tutorials.wisdomleaftest.network

import com.akshai.tutorials.wisdomleaftest.entity.ListApiResponse
import retrofit2.Response


/**
 * Created by ATM on 20/October/2022
 */

interface ApiHelper {
    suspend fun getApi(): Response<List<ListApiResponse>>
}