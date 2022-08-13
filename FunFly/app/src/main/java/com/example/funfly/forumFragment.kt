package com.example.funfly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_forum.*


class forumFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView4.setOnClickListener{
            Navigation.findNavController(it).navigate(forumFragmentDirections.actionForumFragmentToStatusFragment())
        }
        imageView5.setOnClickListener{
            Navigation.findNavController(it).navigate(forumFragmentDirections.actionForumFragmentToEntertainmentFragment())
        }
        imageView6.setOnClickListener{
            Navigation.findNavController(it).navigate(forumFragmentDirections.actionForumFragmentToProfileFragment())
        }
    }


}