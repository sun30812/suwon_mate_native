package com.sn30.suwonuniv.info.suwonmate_native.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.models.InfoCardItem

class InfoListViewAdapter(private val dataList: List<InfoCardItem>): RecyclerView.Adapter<InfoListViewAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(v: View, siteCode: String)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_layout, parent, false)
        view.isClickable = true
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content
        holder.itemView.setOnClickListener {
            mListener?.onItemClicked(it, dataList[position].link)
        }
    }

    override fun getItemCount(): Int = dataList.size
}