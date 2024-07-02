package com.example.fetchrewardsce_api30.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsce_api30.R
import com.example.fetchrewardsce_api30.model.HireListItem
import com.example.fetchrewardsce_api30.adapter.viewHolder.HeaderViewHolder
import com.example.fetchrewardsce_api30.adapter.viewHolder.ItemViewHolder

private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 1

class HireListAdapter(private val groupedData: Map<Int, List<HireListItem>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<Any>()

    init {
        for ((listId, items) in groupedData) {
            dataList.add(listId)
            dataList.addAll(items)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] is Int) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.hire_list_header, parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.hire_list_item_card, parent, false)
                ItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.headerText.text = "List ID: ${(dataList[position] as Int)}"

                val layoutParams = holder.headerLayout.layoutParams as RecyclerView.LayoutParams
                layoutParams.topMargin = if (position == 0 || dataList[position - 1] is Int) 0 else 200 // Adjust the margin value as needed
                holder.headerLayout.layoutParams = layoutParams
            }
            is ItemViewHolder -> holder.itemName.text = (dataList[position] as HireListItem).name
        }
    }

    override fun getItemCount(): Int = dataList.size
}