package com.example.fetchrewardsce_api30

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsce_api30.adapter.HireListAdapter
import com.example.fetchrewardsce_api30.repository.HireList
import com.example.fetchrewardsce_api30.util.ApiListGetCallback


/**
 *
 * Added custom theming for the app, with a custom color palette and dark mode.
 *
 * */
class MainActivity : AppCompatActivity(), ApiListGetCallback {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HireListAdapter
    private lateinit var hireList: HireList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        val appBar = supportActionBar
        appBar!!.title = "Fetch Rewards CE"


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        recyclerView = findViewById(R.id.hireListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        hireList = HireList(this)
    }


    /**
     * Called when the data is loaded from the API.
     * This is where the RecyclerView is populated with the list of hire items.
     */
    override fun onDataLoaded() {
        val groupedData = hireList.getHireList()
        adapter = HireListAdapter(groupedData)
        recyclerView.adapter = adapter
    }
}