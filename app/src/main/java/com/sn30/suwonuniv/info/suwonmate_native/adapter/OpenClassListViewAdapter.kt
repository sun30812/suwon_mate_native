package com.sn30.suwonuniv.info.suwonmate_native.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.models.ClassDetailInfo

class OpenClassListViewAdapter(private val dataList: List<DataSnapshot>): RecyclerView.Adapter<OpenClassListViewAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(v: View, data: ClassDetailInfo)
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
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].child("subjtNm").value.toString()
        holder.subTitle.text = "${ dataList[position].child("ltrPrfsNm").value ?: "이름 공개 안됨"}"
        holder.content.text =
            "${dataList[position].child("estbMjorNm").value ?: "학부 전체"},${dataList[position].child("facDvnm").value}, ${
                
                dataList[position].child("timtSmryCn").value ?: "공개 안됨"
            }"
        val info = ClassDetailInfo(
            targetGrade = "${dataList[position].child("trgtGrdeCd").value ?: " 공개 안됨"}",
            targetDepart = "${dataList[position].child("estbDpmjNm").value ?: "공개 안됨"}",
            targetMajor = "${dataList[position].child("estbMjorNm").value ?: "학부 전체(전공 없음)"}",
            subjectCode = "${dataList[position].child("subjtCd").value}-${dataList[position].child("diclNo").value}",
            openYear = "${dataList[position].child("subjtEstbYear").value ?: "공개 안됨"}",
            subjectKind = "${dataList[position].child("facDvnm").value ?: "공개 안됨"}",
            point = "${dataList[position].child("point").value ?: "공개 안됨"}",
            gender = "${dataList[position].child("sexCdNm").value ?: "공개 안됨"}",
            name = "${dataList[position].child("ltrPrfsNm").value ?: "공개 안됨"}",
            workGrade = "${dataList[position].child("clsfNm").value ?: "공개 안됨"}",
            location = "${dataList[position].child("timtSmryCn").value ?: "공개 안됨"}",
            region = "${dataList[position].child("cltTerrNm").value ?: "해당 없음"}",
            language = "${dataList[position].child("lssnLangNm").value ?: "공개 안됨"}",
            promise = "${dataList[position].child("hffcStatNm").value ?: "공개 안됨"}",
            way = "${dataList[position].child("capprTypeNm").value ?: "공개 안됨"}",
        )
        holder.itemView.setOnClickListener {
            mListener?.onItemClicked(it, info)
        }
    }

    override fun getItemCount(): Int = dataList.size
}