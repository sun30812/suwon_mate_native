package com.sn30.suwonuniv.info.suwonmate_native.view

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.databinding.OpenClassDetailBinding
import com.sn30.suwonuniv.info.suwonmate_native.models.ClassDetailInfo

class OpenClassDetailActivity : AppCompatActivity() {
    private lateinit var binding: OpenClassDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpenClassDetailBinding.inflate(layoutInflater)
        val data: ClassDetailInfo = intent.getSerializableExtra("data") as ClassDetailInfo
        setContentView(binding.root)
        binding.targetStudentBar.titleIcon.setImageResource(R.drawable.ic_outline_assignment_ind_24)
        binding.targetStudentBar.title.setText(R.string.target_student)
        binding.tagetGrade.text = getString(R.string.target_grade, data.targetGrade)
        binding.tagetDepart.text = getString(R.string.target_dept, data.targetDepart)
        binding.tagetMajor.text = getString(R.string.target_major, data.targetMajor)

        binding.subjectInfoBar.titleIcon.setImageResource(R.drawable.ic_outline_class_24)
        binding.subjectInfoBar.title.setText(R.string.subject_info)
        binding.subjectCode.text = getString(R.string.subject_code, data.subjectCode)
        binding.openYear.text = getString(R.string.open_year, data.openYear)
        binding.subjectKind.text = getString(R.string.kind_subject, data.subjectKind)
        binding.point.text = getString(R.string.point, data.point)

        binding.professorInfoBar.titleIcon.setImageResource(R.drawable.ic_outline_work_outline_24)
        binding.professorInfoBar.title.setText(R.string.professor_info)
        binding.gender.text = getString(R.string.gender, data.gender)
        binding.name.text = getString(R.string.name, data.name)
        binding.workGrade.text = getString(R.string.open_year, data.openYear)
        binding.copy.setOnClickListener {
            val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.setPrimaryClip(ClipData.newPlainText("Subject Code",data.subjectCode))
            Snackbar.make(it, R.string.subject_code_copied, Snackbar.LENGTH_SHORT).show()
        }

        binding.classInfoBar.titleIcon.setImageResource(R.drawable.ic_school)
        binding.classInfoBar.title.setText(R.string.relate_info_study)
        binding.workingDay.text = getString(R.string.location, data.location)
        binding.region.text = getString(R.string.ge_class_region, data.region)
        binding.language.text = getString(R.string.language, data.language)

        binding.extraInfoBar.titleIcon.setImageResource(R.drawable.ic_info)
        binding.extraInfoBar.title.setText(R.string.extra_info)
        binding.promise.text = getString(R.string.promise, data.promise)
        binding.studyWay.text = getString(R.string.method, data.way)
    }
}