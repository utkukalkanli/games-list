package com.example.trendyol_internship.ui.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.trendyol_internship.R
import com.example.trendyol_internship.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>() // auto-generated class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        binding.apply {
            val gameID = args.id
            println("DETAIL FRAGMENT: $gameID")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


}