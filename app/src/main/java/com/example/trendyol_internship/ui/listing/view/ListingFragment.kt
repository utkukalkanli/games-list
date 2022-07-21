package com.example.trendyol_internship.ui.listing.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trendyol_internship.R
import com.example.trendyol_internship.databinding.FragmentListingBinding
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import com.example.trendyol_internship.ui.listing.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.flow.collectLatest


class ListingFragment : Fragment() {

    private val viewModel by viewModels<ListingViewModel>()
    private var listingAdapter = ListingAdapter()

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
        (activity as AppCompatActivity).supportActionBar?.title = "Games"
        // Inflate the layout for this fragment
        _binding = FragmentListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // fragment'ımıza viewmodel bağlıyoruz
        initRecyclerView()
        observeLiveData()
        setupCustomBar(view)
    }

    private fun setupCustomBar(view: View) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("KEY WORD: $query")
                binding.searchView.clearFocus()
                val action = ListingFragmentDirections.actionListingFragmentToSearchFragment(query)
                Navigation.findNavController(view).navigate(action)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    println("QUERY EMPTY !!!")
                    //navigationController.navigate(R.id.listingFragment)
                }
                return false
            }
        }
        )
    }

    private fun observeLiveData() {
        viewModel.paginatedGameData.observe(viewLifecycleOwner, Observer {
            listingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    private fun initRecyclerView() {
        gameListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapter = listingAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

