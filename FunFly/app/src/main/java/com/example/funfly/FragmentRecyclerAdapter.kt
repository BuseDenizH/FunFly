package com.example.funfly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FragmentRecyclerAdapter (val id : Int) : RecyclerView.Adapter<FragmentRecyclerAdapter.idHolder>(){

    class idHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): idHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.fragment_forum, parent, false) // when app opens if login is successful first page who forum will show to user
        return idHolder(view)
    }

    override fun onBindViewHolder(holder: idHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return id
    }
}