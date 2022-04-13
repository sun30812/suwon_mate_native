package com.sn30.suwonuniv.info.suwonmate_native.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sn30.suwonuniv.info.suwonmate_native.adapter.InfoListViewAdapter
import com.sn30.suwonuniv.info.suwonmate_native.models.InfoCardItem
import com.sn30.suwonuniv.info.suwonmate_native.models.SimpleCardItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URI

class InfoActivity : AppCompatActivity() {
    private val list = arrayListOf<InfoCardItem>()
    private suspend fun getData():Boolean = withContext(Dispatchers.IO) {
        var complete = false
        val connection = Jsoup.connect("https://www.suwon.ac.kr/index.html?menuno=674")
        runCatching {
            val doc = connection.get()
            val rows: Element = doc.getElementsByClass("board_basic_list")[0]
            for(i in 0..rows.getElementsByClass("subject").size) {
                val item = InfoCardItem(rows.getElementsByClass("subject")[i].text().toString().trim(),
                    rows.getElementsByClass("info")[i].getElementsByClass("date")[0].text().toString() +
                "/"+rows.getElementsByClass("info")[i].getElementsByClass("hit")[0].text().toString(), rows
                        .getElementsByClass("subject")[i]
                        .html()
                        .split(',')[2]
                        .split(')')[0])
                list.add(item)
                if (list.size > 10)
                    complete = true
            }
        }
        complete
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainView = LinearLayoutCompat(this)
        mainView.orientation = LinearLayoutCompat.VERTICAL
        setContentView(mainView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mainListView = RecyclerView(this)
        val adapter = InfoListViewAdapter(list)
        adapter.setOnItemClickListener(
            object : InfoListViewAdapter.OnItemClickListener {
                override fun onItemClicked(v: View, siteCode: String) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.suwon.ac.kr/index.html?menuno=674&bbsno=$siteCode&boardno=$siteCode&siteno=37&act=view")))
                }
            }
        )
        mainListView.visibility = RecyclerView.GONE
        mainListView.adapter = adapter
        mainListView.layoutManager = LinearLayoutManager(this)
        val loading = ProgressBar(this)
        mainView.addView(loading)
        mainView.addView(mainListView)
        CoroutineScope(Dispatchers.Main).launch {
            getData()
            mainListView.visibility = View.VISIBLE
            loading.visibility = View.GONE
            adapter.notifyItemInserted(0)
        }
    }
}