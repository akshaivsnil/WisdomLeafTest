package com.akshai.tutorials.wisdomleaftest.network

import com.akshai.tutorials.wisdomleaftest.entity.ListApiResponse
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by ATM on 20/October/2022
 */

class ApiHelperImpl @Inject constructor(
    val apiServices: ApiServices,
) : ApiHelper {

    override suspend fun getApi(): Response<List<ListApiResponse>> {
        return  apiServices.getListData(page =2, limit = 20)
    }
}