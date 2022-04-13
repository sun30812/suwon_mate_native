package com.sn30.suwonuniv.info.suwonmate_native.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.models.CardItem

class HelpListAdapter(private val dataList: Array<CardItem>) : RecyclerView.Adapter<HelpListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val content: TextView = view.findViewById(R.id.content)
        val icon: ImageView = view.findViewById(R.id.title_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content
        holder.icon.setImageResource(dataList[position].iconId)
    }

    override fun getItemCount() = dataList.size

}