package com.example.covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //database = Firebase.database.reference
        register_Btn.setOnClickListener{
            writeUser()

        }
    }
    private fun writeUser(){
        val email = email_Field.text.toString()
        val username = username_Field.text.toString()
        val name = name_Field.text.toString()
        val password = password_Field.text.toString()
        val user = UserData(email, name, username, password)
        val intent = Intent(this, LoginActivity::class.java)

        if(email.isEmpty()){
            email_Field.error = "Please enter a email"
            return
        }else if(name.isEmpty()){
            name_Field.error = "Please enter a name"
            return
        }else if(username.isEmpty()){
            username_Field.error = "Please enter a username"
            return
        }else if(password.isEmpty()){
            password_Field.error = "Please enter a password"
            return
        }
        val database = FirebaseDatabase.getInstance().getReference("Users")
        val userID = database.push().key

        database.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        username_Field.error = "Username Taken"
                        return
                    }else{
                        database.child(userID.toString()).setValue(user)
                        startActivity(intent)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })


    }



}


