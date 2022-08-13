package com.example.funfly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_entertainment2.*
import kotlinx.android.synthetic.main.fragment_forum.*
import kotlinx.android.synthetic.main.fragment_forum.imageView3
import kotlinx.android.synthetic.main.fragment_forum.imageView4
import kotlinx.android.synthetic.main.fragment_forum.imageView6
import kotlinx.android.synthetic.main.fragment_question2.*


class questionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imageView3.setOnClickListener{
            Navigation.findNavController(it).navigate(questionFragmentDirections.actionQuestionFragmentToForumFragment())
        }

        imageView4.setOnClickListener{
            Navigation.findNavController(it).navigate(questionFragmentDirections.actionQuestionFragmentToStatusFragment())
        }
        imageView6.setOnClickListener{
            Navigation.findNavController(it).navigate(questionFragmentDirections.actionQuestionFragmentToProfileFragment())
        }

        button15.setOnClickListener {
            Navigation.findNavController(it).navigate(questionFragmentDirections.actionQuestionFragmentToEntertainmentFragment())
        }


    }

}