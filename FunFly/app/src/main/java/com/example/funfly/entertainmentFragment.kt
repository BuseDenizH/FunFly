package com.example.funfly

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_entertainment2.*
import kotlinx.android.synthetic.main.fragment_forum.*
import kotlinx.android.synthetic.main.fragment_forum.imageView3
import kotlinx.android.synthetic.main.fragment_forum.imageView4
import kotlinx.android.synthetic.main.fragment_forum.imageView6


class entertainmentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView3.setOnClickListener{
            Navigation.findNavController(it).navigate(entertainmentFragmentDirections.actionEntertainmentFragmentToForumFragment())
        }

        imageView4.setOnClickListener{
            Navigation.findNavController(it).navigate(entertainmentFragmentDirections.actionEntertainmentFragmentToStatusFragment())
        }
        imageView6.setOnClickListener{
            Navigation.findNavController(it).navigate(entertainmentFragmentDirections.actionEntertainmentFragmentToProfileFragment())
        }

        imageView35.setOnClickListener{
            Navigation.findNavController(it).navigate(entertainmentFragmentDirections.actionEntertainmentFragmentToTodoFragment())
        }
        imageView36.setOnClickListener{
            Navigation.findNavController(it).navigate(entertainmentFragmentDirections.actionEntertainmentFragmentToQuestionFragment())
        }
        imageView37.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.shopandmiles.com/"))
            startActivity(browserIntent)
        }


    }



}