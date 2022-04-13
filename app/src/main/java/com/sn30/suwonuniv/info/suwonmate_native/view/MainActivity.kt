package com.sn30.suwonuniv.info.suwonmate_native.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sn30.suwonuniv.info.suwonmate_native.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.help.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }
        binding.schedule.setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
        }
        binding.settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.notification.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }
        binding.inquire.setOnClickListener {
            startActivity(Intent(this, OpenClassActivity::class.java))
        }
    }
}