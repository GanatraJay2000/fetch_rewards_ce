package com.example.fetchrewardsce_api30.util

/**
 * This interface defines a callback to be invoked when the data is loaded from the API.
 * It is used in the HireList class to notify the MainActivity when the list of hire items is loaded.
 */
interface ApiListGetCallback {
    fun onDataLoaded()
}