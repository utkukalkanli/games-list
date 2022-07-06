package com.example.trendyol_internship.ui.listing.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendyol_internship.R
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import com.example.trendyol_internship.ui.listing.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.fragment_listing.*

class ListingFragment : Fragment() {

    private lateinit var viewModel: ListingViewModel
    private val listingAdapter = ListingAdapter(arrayListOf())


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
        viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
        viewModel.refreshData()

        //gameListRecyclerView.layoutManager = LinearLayoutManager(context)
        gameListRecyclerView.layoutManager = GridLayoutManager(context,2)
        gameListRecyclerView.adapter = listingAdapter

        swipeRefreshLayout.setOnRefreshListener {
            gameListRecyclerView.visibility = View.GONE
            gameListError.visibility = View.GONE
            gameListLoading.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.gameList.observe(viewLifecycleOwner, Observer {gameList ->
            gameList?.let {
                // if gamelist not empty, make recyclerview visible
                gameListRecyclerView.visibility = View.VISIBLE
                listingAdapter.updateGameList(gameList)
            }
        })
        viewModel.gameListError.observe(viewLifecycleOwner, Observer { listingError->
            listingError?.let {
                // eğer error mesajı true ise
                if(it){
                    gameListError.visibility = View.VISIBLE
                }else{
                    gameListError.visibility = View.GONE
                }
            }
        })

        viewModel.gameListLoading.observe(viewLifecycleOwner, Observer { gamesLoading->
            gamesLoading?.let {
                if(it){
                    gameListLoading.visibility = View.VISIBLE
                    gameListRecyclerView.visibility = View.GONE
                    gameListError.visibility = View.GONE
                }else{
                    gameListLoading.visibility = View.GONE

                }
            }

        })
    }

}

