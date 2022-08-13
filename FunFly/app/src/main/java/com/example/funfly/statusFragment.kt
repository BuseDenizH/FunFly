package com.example.funfly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_forum.imageView3
import kotlinx.android.synthetic.main.fragment_forum.imageView5
import kotlinx.android.synthetic.main.fragment_forum.imageView6
import kotlinx.android.synthetic.main.fragment_status.*
import java.lang.Math.abs


class statusFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = FirebaseDatabase.getInstance().reference
        var userID = -1
        activity?.let {
            val userDB : android.database.sqlite.SQLiteDatabase = it.openOrCreateDatabase("UserID",
                AppCompatActivity.MODE_PRIVATE, null)
            try{
                userDB.execSQL("CREATE TABLE IF NOT EXISTS userID (id INTEGER PRIMARY KEY)")

                val cursor = userDB.rawQuery("SELECT * FROM userID", null)
                val idColumdIndex = cursor.getColumnIndex("id")
                while (cursor.moveToNext()){
                    userID = cursor.getInt(idColumdIndex)
                }
                cursor.close()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        val strId = userID.toString()
        val myPoint = 1453
        println("my id ${strId}")


        val getData = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var counter = 0
                var userRatioMap = mutableMapOf("one" to 1)
                for (id in snapshot.children){
                    // ucus kontrolu eklenecek
                    if (id.key.toString() != "0" && id.key.toString() != strId){
                        val temp = id.child("catPoint").getValue().toString().toInt()
                        val userID = id.key.toString()

                        val matchPoint = matchMe(myPoint,temp)

                        userRatioMap.put(userID, matchPoint)

                        counter += 1

                    }

                }

                val userIDList : Array<String> = Array(counter) { "" }
                val usernameRatio: Array<Int> = Array(counter) { 0 }

                userRatioMap = userRatioMap.toList().sortedByDescending { (_,value) -> value }.toMap() as MutableMap<String, Int>
                val userList = userRatioMap.keys.toList()
                val ratioList = userRatioMap.values.toList()
                for (i in 0..counter-1){
                    userIDList[i] = userList[i]
                    usernameRatio[i] = ratioList[i]
                }
                val layoutmanager = LinearLayoutManager(activity)
                recyclerView.layoutManager = layoutmanager

                val adapter = RecyclerAdapter(userIDList, usernameRatio)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        database.addValueEventListener(getData)

    }
    fun matchMe(myPoint: Int, userPoint: Int) : Int{
        var match = 55
        val mPoint = myPoint.toString()
        val uPoint = userPoint.toString()

        if(mPoint[0] == uPoint[0]){
            match += 10
        }
        else{
            match += abs(mPoint[0].toInt()-uPoint[0].toInt())
        }
        if(mPoint[1] == uPoint[1]){
            match += 10
        }
        else{
            match += abs(mPoint[1].toInt()-uPoint[1].toInt())
        }
        if(mPoint[2] == uPoint[2]){
            match += 10
        }
        else{
            match += abs(mPoint[2].toInt()-uPoint[2].toInt())
        }
        if(mPoint[3] == uPoint[3]){
            match += 10
        }
        else{
            match += abs(mPoint[3].toInt()-uPoint[3].toInt())
        }

        return match
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        imageView3.setOnClickListener{
            Navigation.findNavController(it).navigate(statusFragmentDirections.actionStatusFragmentToForumFragment())
        }

        imageView5.setOnClickListener{
            Navigation.findNavController(it).navigate(statusFragmentDirections.actionStatusFragmentToEntertainmentFragment())
        }
        imageView6.setOnClickListener{
            Navigation.findNavController(it).navigate(statusFragmentDirections.actionStatusFragmentToProfileFragment())
        }

    }
}