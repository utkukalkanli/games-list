package com.example.trendyol_internship.ui.listing.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trendyol_internship.MainActivity
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.databinding.FragmentListingBinding
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import com.example.trendyol_internship.ui.listing.viewmodel.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_listing.*


@AndroidEntryPoint
class ListingFragment : Fragment(), ListingAdapter.OnItemClickListener {

    private val viewModel by viewModels<ListingViewModel>()
    private val listingAdapter = ListingAdapter(this)

    /**
     * When we use view binding in the fragment we have to pay special attention because the view of a fragment can be destroyed while the
     * fragment itself is still in memory. If this is the case we have to null out our binding variable otherwise it will keep
     * unnecessary reference to the whole view hierarchy which is a memory leak.
     */
    private var _binding: FragmentListingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeLiveData()
        initializeLoadStateListener()
        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.search_item)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchGames(query)
                    binding.gameListRecyclerView.scrollToPosition(0)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    viewModel.searchGames("")
                    searchView.clearFocus()
                }
                return true
            }
        })
    }

    private fun initializeLoadStateListener() {
        listingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                gameListRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // for the empty view
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && listingAdapter.itemCount < 1) {
                    gameListRecyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.games.observe(viewLifecycleOwner, Observer {
            listingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    private fun initRecyclerView() {
        gameListRecyclerView.apply {
            itemAnimator =
                null // recyclerview data's her degistiginde diffutil compare ederken eski dataset flashlıyor ve cok cirkin görünüyor, bu recyclerview animasyonlarını kapatarak bunun önüne geçiyor
            layoutManager = GridLayoutManager(context, 2)
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapter = listingAdapter
            gameListRecyclerView.adapter.apply { }
            /**
            binding.buttonRetry.setOnClickListener(
            // adapter.retry()
            )*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(game: Game) {
        val action = game.id?.let {
            ListingFragmentDirections.actionListingFragmentToDetailFragment(
                it
            )
        }
        if (action != null) {
            findNavController().navigate(action)
        }
    }


}

