package com.sn30.suwonuniv.info.suwonmate_native.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sn30.suwonuniv.info.suwonmate_native.R
import com.sn30.suwonuniv.info.suwonmate_native.databinding.OpenClassDetailBinding

class OpenClassDetailActivity : AppCompatActivity() {
    private lateinit var binding: OpenClassDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpenClassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.targetStudentBar.titleIcon.setImageResource(R.drawable.ic_outline_assignment_ind_24)
        binding.targetStudentBar.title.setText(R.string.target_student)

        binding.subjectInfoBar.titleIcon.setImageResource(R.drawable.ic_outline_class_24)
        binding.subjectInfoBar.title.setText(R.string.subject_info)

        binding.professorInfoBar.titleIcon.setImageResource(R.drawable.ic_outline_work_outline_24)
        binding.professorInfoBar.title.setText(R.string.professor_info)

        binding.classInfoBar.titleIcon.setImageResource(R.drawable.ic_school)
        binding.classInfoBar.title.setText(R.string.relate_info_study)

        binding.extraInfoBar.titleIcon.setImageResource(R.drawable.ic_info)
        binding.extraInfoBar.title.setText(R.string.extra_info)
    }
}