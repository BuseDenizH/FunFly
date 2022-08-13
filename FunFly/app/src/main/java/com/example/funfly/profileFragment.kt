package com.example.funfly

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.Person.fromBundle
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_forum.*
import kotlinx.android.synthetic.main.fragment_forum.imageView3
import kotlinx.android.synthetic.main.fragment_forum.imageView4
import kotlinx.android.synthetic.main.fragment_forum.imageView5
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File


class profileFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView3.setOnClickListener{
            Navigation.findNavController(it).navigate(profileFragmentDirections.actionProfileFragmentToForumFragment())
        }
        imageView4.setOnClickListener{
            Navigation.findNavController(it).navigate(profileFragmentDirections.actionProfileFragmentToStatusFragment())
        }

        imageView5.setOnClickListener{
            Navigation.findNavController(it).navigate(profileFragmentDirections.actionProfileFragmentToEntertainmentFragment())
        }

        arguments?.let {
            val userId = profileFragmentArgs.fromBundle(it).name
            val mPoint = profileFragmentArgs.fromBundle(it).matchpoint

            if (mPoint != -1){


                textView20.setText("Match $mPoint")

                val progressDiaglog = ProgressDialog(activity)
                progressDiaglog.setMessage("Calculating ...")
                progressDiaglog.setCancelable(false)
                progressDiaglog.show()

                var name = "tempNAME"

                val database = FirebaseDatabase.getInstance().reference
                database.child(userId).child("name").get().addOnSuccessListener {
                    name = it.value.toString()
                    textView15.setText(name)
                }



                val storageRef = FirebaseStorage.getInstance().reference.child("images/${userId}.jpeg")

                val localFile = File.createTempFile("tempImage", "jpeg")

                storageRef.getFile(localFile).addOnSuccessListener {

                    if (progressDiaglog.isShowing)
                        progressDiaglog.dismiss()

                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    imageView22.setImageBitmap(bitmap)

                }.addOnFailureListener{
                    if (progressDiaglog.isShowing)
                        progressDiaglog.dismiss()
                    Toast.makeText(activity, "You are not connected to internet!", Toast.LENGTH_SHORT).show()
                }
                imageView16.visibility = View.INVISIBLE
            }
            else{
                var id : Int = -1
                activity?.let {
                    val userDB : android.database.sqlite.SQLiteDatabase = it.openOrCreateDatabase("UserID",
                        AppCompatActivity.MODE_PRIVATE, null)
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
                }

                val progressDiaglog = ProgressDialog(activity)
                progressDiaglog.setMessage("Fetching image...")
                progressDiaglog.setCancelable(false)
                progressDiaglog.show()

                val storageRef = FirebaseStorage.getInstance().reference.child("images/${id}.jpeg")

                val localFile = File.createTempFile("tempImage", "jpeg")

                storageRef.getFile(localFile).addOnSuccessListener {

                    if (progressDiaglog.isShowing)
                        progressDiaglog.dismiss()

                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    imageView22.setImageBitmap(bitmap)

                }.addOnFailureListener{
                    if (progressDiaglog.isShowing)
                        progressDiaglog.dismiss()
                    Toast.makeText(activity, "You are not connected to internet!", Toast.LENGTH_SHORT).show()
                }

                imageView16.setOnClickListener {
                    activity?.let {
                        it.deleteDatabase("UserID")
                        val intent = Intent(it.applicationContext, LoginActivity::class.java)

                        startActivity(intent)

                    }

                }
            }
        }
    }
}