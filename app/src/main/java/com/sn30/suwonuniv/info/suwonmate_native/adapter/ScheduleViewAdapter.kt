package com.sn30.suwonuniv.info.suwonmate_native.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.models.SimpleCardItem

class ScheduleViewAdapter(private val dataList: List<SimpleCardItem>): RecyclerView.Adapter<ScheduleViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content
    }

    override fun getItemCount(): Int = dataList.size
}