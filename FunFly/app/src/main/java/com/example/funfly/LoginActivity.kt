package com.example.funfly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        println("Login Activity?")
    }

    fun login(view: View){
        val userMail = email.text.toString()
        val userPass = password.text.toString()

        val database = FirebaseDatabase.getInstance().reference
        val userDB : android.database.sqlite.SQLiteDatabase = this.openOrCreateDatabase("UserID", MODE_PRIVATE, null)
        var getData = object : ValueEventListener{
            var auth = 0
            override fun onDataChange(snapshot: DataSnapshot) {
                for (id in snapshot.children){
                    if ( (id.child("email").getValue().toString() == userMail) && (id.child("password").getValue().toString() == userPass) ){

                        val sqlString = "INSERT INTO userID (id) VALUES (? )"
                        val statement = userDB.compileStatement(sqlString)
                        statement.bindString(1,id.key.toString())
                        statement.execute()

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        auth = 1
                        break
                    }
                }
                if(auth == 0)
                    passwordWrong.setText("Password or Email is not correct!")
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getData)

    }

    fun register(view: View){
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}