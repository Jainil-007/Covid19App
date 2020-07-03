package com.example.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_setting.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val username = intent.getStringExtra("Username")
        title_Text.text = "Welcome " + username
        update_Btn.setOnClickListener(){
            changeDetails()

        }

    }


    private fun changeDetails(){
        val database = FirebaseDatabase.getInstance().getReference("Users")
        val email = newEmail_Field.text
        val password = newPassword_Field.text
        val username = intent.getStringExtra("Username")

        if(email.isEmpty()){
            newEmail_Field.error = "Please enter a email"
            return
        }else if(password.isEmpty()){
            newPassword_Field.error = "Please enter a password"
            return
        }

        database.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(h in snapshot.children){
                            val userID = h.key
                            val name = h.child("name").getValue()
                            val user = UserData(email.toString(), name.toString(), username, password.toString())
                            database.child(userID.toString()).setValue(user)
                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
}

}