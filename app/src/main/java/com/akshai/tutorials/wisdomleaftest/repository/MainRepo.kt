package com.akshai.tutorials.wisdomleaftest.repository

import com.akshai.tutorials.wisdomleaftest.entity.ListApiResponse
import com.akshai.tutorials.wisdomleaftest.network.ApiHelper
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by ATM on 20/October/2022
 */

class MainRepo @Inject constructor(
    private val apiHelper: ApiHelper,
)  {

    suspend fun getListFromApiHelper() : Response<List<ListApiResponse>> {
        return apiHelper.getApi()
    }

}