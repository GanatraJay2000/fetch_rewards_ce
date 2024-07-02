package com.example.fetchrewardsce_api30.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsce_api30.R

/**
 * ViewHolder to display the list ID as a separate header layout in the RecyclerView
 * to make it user-friendly.
 */
class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val headerText: TextView = view.findViewById(R.id.headerText)
    val headerLayout: View = view
}