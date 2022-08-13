package com.example.funfly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.match.view.*

class RecyclerAdapter(val userIDList: Array<String>, val usernameRatio: Array<Int>) :
    RecyclerView.Adapter<RecyclerAdapter.UsernameHolder>() {

    class UsernameHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsernameHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.match, parent, false)
        return UsernameHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsernameHolder, position: Int) {
        val database = FirebaseDatabase.getInstance().reference
        database.child(userIDList.get(position)).get().addOnSuccessListener{
            var name = it.child("name").getValue().toString()
            if (name.length > 10){
                name = name.substring(0,10)
            }
            holder.itemView.username.text = name
        }
        //holder.itemView.username.text = userIDList.get(position)
        holder.itemView.point.text = usernameRatio.get(position).toString()
        holder.itemView.setOnClickListener{
            Navigation.findNavController(it).navigate(statusFragmentDirections.actionStatusFragmentToProfileFragment( userIDList.get(position) , usernameRatio.get(position)))
        }
        // heyyy buraya bakkk
        //holder.itemView.buy.setOnClickListener{
        //
        //}
    }

    override fun getItemCount(): Int {
        return userIDList.size
    }


}