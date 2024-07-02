package com.example.fetchrewardsce_api30.repository

import android.util.Log
import com.example.fetchrewardsce_api30.model.HireListItem
import com.example.fetchrewardsce_api30.network.RetrofitClient
import com.example.fetchrewardsce_api30.util.ApiListGetCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * This class is responsible for fetching and managing the list of hire items.
 * It fetches the list from the API and provides a method to get the list.
 *
 * @property callback A callback to be invoked when the data is loaded from the API.
 */
class HireList(private val callback: ApiListGetCallback) {
    private var _hireList:List<HireListItem> = ArrayList()

    init {
        fetchHireListFromApi()
    }

    private fun fetchHireListFromApi() {
        val call = RetrofitClient.instance.getHireList()
        call.enqueue(object : Callback<List<HireListItem>> {
            override fun onResponse(call: Call<List<HireListItem>>, response: Response<List<HireListItem>>) {
                if (response.isSuccessful) {
                    _hireList = response.body() ?: ArrayList()
                    callback.onDataLoaded()
                } else {
                    Log.e("HireList", "API call failed with response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<HireListItem>>, t: Throwable) {
                Log.e("HireList", "API call failed", t)
            }
        })
    }

    /**
     * Returns the list of hire items grouped by sorted listId.
     * The items in each group are sorted by name.
     * Items with a blank or null name are excluded.
     *
     * @return A map where the keys are the listIds and the values are lists of hire items with that listId.
     */
    fun getHireList(): Map<Int, List<HireListItem>> {
        return _hireList
            .filter { !it.name.isNullOrBlank() }
            .sortedWith(compareBy({ it.listId }, { it.name }))
            .groupBy { it.listId }
    }
}