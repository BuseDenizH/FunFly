package com.example.funfly

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var id : Int = -1
        val userDB : android.database.sqlite.SQLiteDatabase = this.openOrCreateDatabase("UserID", MODE_PRIVATE, null)
        try{
            userDB.execSQL("CREATE TABLE IF NOT EXISTS userID (id INTEGER PRIMARY KEY)")

            val cursor = userDB.rawQuery("SELECT * FROM userID", null)
            val idColumdIndex = cursor.getColumnIndex("id")
            while (cursor.moveToNext()){
                id = cursor.getInt(idColumdIndex)
            }
            cursor.close()
        }catch (e:Exception){
            e.printStackTrace()
        }

        if(id == -1){
            println("NOT USER FOUND")
            // login Activity Started
            val intent = Intent(applicationContext, LoginActivity::class.java)

            startActivity(intent)

            finish()


        }else{
            println("User found by $id")
        }

    }



}