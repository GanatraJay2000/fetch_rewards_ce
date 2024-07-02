package com.example.fetchrewardsce_api30.network

import com.example.fetchrewardsce_api30.model.HireListItem
import retrofit2.Call
import retrofit2.http.GET



/**
 * Using Retrofit making the API call for fetching the list of hire items.
 */
interface IApiService {
    @GET("hiring.json")
    fun getHireList(): Call<List<HireListItem>>
}