package com.sn30.suwonuniv.info.suwonmate_native.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.databinding.ScheduleBinding
import com.sn30.suwonuniv.info.suwonmate_native.adapter.ScheduleViewAdapter
import com.sn30.suwonuniv.info.suwonmate_native.models.SimpleCardItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.time.LocalDate
import java.time.LocalDateTime

class ScheduleActivity: AppCompatActivity() {
    private lateinit var binding: ScheduleBinding
    private val url = "https://www.suwon.ac.kr/index.html?menuno=727"
    override fun onCreate(savedInstanceState: Bundle?) {
        val list = arrayListOf<SimpleCardItem>()
        super.onCreate(savedInstanceState)
        binding = ScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val listView = binding.scheduleMainView

        val adapter = ScheduleViewAdapter(list)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)
        suspend fun getData():Boolean = withContext(Dispatchers.IO) {
            val connection = Jsoup.connect(url)
            runCatching {
                val doc = connection.get()
                val rows: Elements = doc.getElementsByClass("contents_table")[0].getElementsByTag("tr")
                rows
                    .forEachIndexed { index, dat ->
                        if (index < 1) return@forEachIndexed

                        val item = SimpleCardItem(dat.getElementsByTag("td").elementAt(1).text(),
                            dat.getElementsByTag("td").elementAt(0).text())
                        list.add(item)
                    }
            }
            true
        }
        fun getTodaySchedule(): String{
            val now = LocalDate.now()
            for (date in list) {
                val data = date.content.replace(Regex("\\([^)]*\\)"), "")
                    .replace(".","-")
                if (now == LocalDate.parse(data.substring(0..9))) {
                    return date.title
                } else if (data.contains('~')) {
                    if ((now >= LocalDate.parse(data.substring(0..9))) &&
                            (now <= LocalDate.parse(data.substring(13..22)))) {
                        return date.title
                    }
                }
            }
            // 오늘날자와 대조하는 날짜를 찾는 알고리즘 가져오기
            return getString(R.string.none)
        }
        CoroutineScope(Dispatchers.Main).launch {
             getData()
            binding.loading.visibility = ProgressBar.GONE
            binding.scheduleRootView.gravity = Gravity.TOP
            binding.scheduleMainView.visibility = RecyclerView.VISIBLE
            binding.today.text = getString(R.string.today_schedule, getTodaySchedule())
            binding.today.visibility = TextView.VISIBLE
            adapter.notifyItemInserted(0)
        }
    }
}