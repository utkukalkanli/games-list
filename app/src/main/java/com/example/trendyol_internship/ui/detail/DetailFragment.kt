package com.example.trendyol_internship.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.trendyol_internship.R
import com.example.trendyol_internship.ui.listing.ListingFragmentDirections
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_listing.*


class DetailFragment : Fragment() {
    private var gameID = 0

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            gameID = DetailFragmentArgs.fromBundle(it).id
            println(gameID)
        }
        /**
        to_listing_fragment_button.setOnClickListener{
            val action = DetailFragmentDirections.actionDetailFragmentToListingFragment()
            Navigation.findNavController(it).navigate(action)
        }
        */
    }
}