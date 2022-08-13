package com.example.funfly

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private var id : Int  = -28
    private val database = FirebaseDatabase.getInstance().reference
    lateinit var ImageUri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database.child("0").child("lastPoint").get().addOnSuccessListener { it ->

            val str = it.value
            id = str.toString().toInt()

        }.addOnFailureListener{
            println("BUYUK PROBLEM")
            id = -2
        }
    }
    fun upload(view: View){
        selectImage()
    }
    fun done(view: View){
        val userDB : android.database.sqlite.SQLiteDatabase = this.openOrCreateDatabase("UserID", MODE_PRIVATE, null)

        // get information from user
        val username = name.text.toString()
        val pNumber = phone.text.toString()
        val emailAdd = email.text.toString()
        val userPass = password.text.toString()
        val catPoint = 9999

        id += 1

        database.child("0").child("lastPoint").setValue( id.toString() )
        database.child(id.toString()).setValue(User(username, pNumber, catPoint.toString(), emailAdd, userPass))

        val sqlString = "INSERT INTO userID (id) VALUES (? )"
        val statement = userDB.compileStatement(sqlString)

        statement.bindString(1,id.toString())
        statement.execute()
        uploadImage(id)

        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun uploadImage(id : Int){
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading file..")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val storageReference = FirebaseStorage.getInstance().getReference("images/${id.toString()}.jpeg")

        storageReference.putFile(ImageUri).
                addOnSuccessListener {

                    imageView7.setImageResource(R.drawable.plane)
                    Toast.makeText(this@RegisterActivity, "Successfuly Uploaded", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
            if(progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@RegisterActivity, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
    private fun selectImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            imageView7.setImageURI(ImageUri)
        }
    }
}


