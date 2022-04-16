package com.sn30.suwonuniv.info.suwonmate_native.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.sn30.suwonuniv.info.suwonmate_native.R

class OpenClassListViewAdapter(private val dataList: List<DataSnapshot>): RecyclerView.Adapter<OpenClassListViewAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(v: View)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val subTitle: TextView = itemView.findViewById(R.id.sub_title)
        val content: TextView = itemView.findViewById(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.class_list_layout, parent, false)
        view.isClickable = true
        view.setOnClickListener {
            mListener?.onItemClicked(it)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].child("subjtNm").value.toString()
        holder.subTitle.text = "${ dataList[position].child("ltrPrfsNm").value ?: "이름 공개 안됨"}"
        holder.content.text = "${dataList[position].child("estbMjorNm").value ?: "학부 전체"},${dataList[position].child("facDvnm").value}, ${dataList[position].child("timtSmryCn").value ?: "공개 안됨"}"
    }

    override fun getItemCount(): Int = dataList.size
}