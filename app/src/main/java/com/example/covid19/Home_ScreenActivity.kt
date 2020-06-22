package com.example.covid19

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.home_screen.*
class Home_ScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        init()
    }
    private fun init() {
        symptoms.setOnClickListener { startActivity(Intent(this@Home_ScreenActivity, Symptom::class.java)) }
        prevention.setOnClickListener{ startActivity(Intent(this@Home_ScreenActivity, Symptom::class.java)) }
        NZGOV.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ConstantValues.NZgov))) }
        Reports.setOnClickListener{ startActivity(Intent(this@Home_ScreenActivity, ReportActivity::class.java)) }

    }
}

