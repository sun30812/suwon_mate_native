package com.sn30.suwonuniv.info.suwonmate_native.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.adapter.HelpListAdapter
import com.sn30.suwonuniv.info.suwonmate_native.databinding.HelpBinding
import com.sn30.suwonuniv.info.suwonmate_native.models.CardItem

class HelpActivity : AppCompatActivity() {
    private val settingArray = arrayOf<CardItem>(
        CardItem(R.drawable.ic_clock,"학사일정", "학교의 일정을 확인할 수 있습니다."),
        CardItem(R.drawable.ic_calender,"개설 강좌 조회", "학부와 전공을 선택하여 과목들을 확인할 수 있습니다.\n과목을 클릭하면 해당 과목의 상세정보도 확인할 수 있습니다.\n교양 영역의 경우 학부를 교양으로 선택하면 고를 수 있습니다.\n"),
        CardItem(R.drawable.ic_alert,"공지사항", "학교의 공지사항을 볼 수 있습니다.\n제목을 클릭하면 세부정보를 확인할 수 있습니다."),
        CardItem(R.drawable.ic_star,"즐겨찾는 과목(베타)", "개설 강좌 조회에서 즐겨찾기에 추가한 과목들만 확인할 수 있습니다.\n\n현재 즐겨찾는 과목 페이지에서 즐겨찾기 제거 시 앱 메인화면으로 나갔다가 들어와야 정상적으로 반영됩니다."),
        CardItem(R.drawable.ic_settings,"설정", "앱과 관련된 설정들을 할 수 있습니다."),
    )

    private lateinit var binding: HelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val listView: RecyclerView = binding.recycle
        val adapter = HelpListAdapter(settingArray)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)
    }
}