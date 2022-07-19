package com.example.trendyol_internship.ui.listing.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendyol_internship.R
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import com.example.trendyol_internship.ui.listing.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.flow.collectLatest

class ListingFragment : Fragment() {

    //private val listingAdapter = ListingAdapter()
    private lateinit var listingAdapter: ListingAdapter

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

        // fragment'ımıza viewmodel bağlıyoruz
        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        gameListRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            val decoration  = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            listingAdapter = ListingAdapter()
            adapter = listingAdapter
        }
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(ListingViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListDataFromAPI().collectLatest {
                listingAdapter.submitData(it)
            }
        }
    }



}

