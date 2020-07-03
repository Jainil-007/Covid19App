package com.example.covid19

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.home_screen.*
import java.util.jar.Manifest

class Home_ScreenActivity : AppCompatActivity() {



    val phoneNumber = "1111"
    val REQUEST_PHONE_CALL = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        init()

    }
    private fun init() {
        symptoms.setOnClickListener { startActivity(Intent(this@Home_ScreenActivity, SymptomActivity::class.java)) }
        prevention.setOnClickListener{ startActivity(Intent(this@Home_ScreenActivity, SymptomActivity::class.java)) }
        NZGOV.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ConstantValues.NZgov))) }
        Reports.setOnClickListener{ startActivity(Intent(this@Home_ScreenActivity, ReportActivity::class.java)) }
        condata.setOnClickListener{ startActivity(Intent(this@Home_ScreenActivity, AffectedCountries::class.java)) }
        qrCodeScanner.setOnClickListener{ startActivity(Intent(this@Home_ScreenActivity, QrCodeActivity::class.java)) }

        val user = intent.getStringExtra("Username")
        settings_Btn.setOnClickListener {
            val send = Intent(this, SettingActivity::class.java)
            send.putExtra("Username", user)
            startActivity(send)
        }

       button.setOnClickListener {
           if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
               ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
           }else{
               startCall()
           }

       }


} @SuppressLint("MissingPermission")
    private  fun startCall(){
    val callIntent = Intent(Intent.ACTION_DIAL)
    callIntent.data = Uri.parse("tel:"+ phoneNumber)

    startActivity(callIntent)}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_PHONE_CALL)startCall()
          }

    }



