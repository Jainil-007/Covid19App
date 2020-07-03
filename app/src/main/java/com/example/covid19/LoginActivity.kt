package com.example.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password_Field
import kotlinx.android.synthetic.main.activity_login.username_Field
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_Btn.setOnClickListener{
            login()
        }

        showRegister_Btn.setOnClickListener{
            val intent =Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }




    }

    private fun login(){
        val username = username_Field.text.toString()
        val password = password_Field.text.toString()
        val database = FirebaseDatabase.getInstance().getReference("Users")
        val home = Intent(this, Home_ScreenActivity::class.java)
        home.putExtra("Username", username)
        if(username.isEmpty()){
            username_Field.error = "Please enter a username"
            return
        }else if(password.isEmpty()){
            password_Field.error = "Please enter a password"
            return
        }

        database.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    for(h in snapshot.children){
                        val user = h.getValue(UserData::class.java)
                        if(user!!.username == username && user!!.password == password){
                            startActivity(home)
                            return
                        }
                    }
                }else{
                    Toast.makeText(this@LoginActivity, "Incorrect Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}