package com.example.trendyol_internship.ui.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.trendyol_internship.R
import kotlinx.android.synthetic.main.fragment_listing.*

class ListingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameID = 5
        /**
        listing_fragment_button.setOnClickListener{
            val action = ListingFragmentDirections.actionListingFragmentToDetailFragment(gameID)
            Navigation.findNavController(it).navigate(action)
        }
        */
    }

}

