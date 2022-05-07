package com.sn30.suwonuniv.info.suwonmate_native.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sn30.suwonuniv.info.suwonmate_native.models.ClassDetailInfo

class AssociateProfessorActivity: AppCompatActivity() {
    override fun onBackPressed() {
        super.onBackPressed()
        val newIntent = Intent(this, OpenClassDetailActivity::class.java)
        newIntent.putExtra("data", intent.getSerializableExtra("data") as ClassDetailInfo)
        startActivity(newIntent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name: String = (intent.getSerializableExtra("data") as ClassDetailInfo).name
        supportActionBar?.title = name + "강의자의 과목"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}