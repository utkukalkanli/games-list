package com.example.trendyol_internship.ui.listing.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trendyol_internship.databinding.FragmentListingBinding
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import com.example.trendyol_internship.ui.listing.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.flow.collectLatest


class ListingFragment : Fragment() {

    private var listingAdapter = ListingAdapter()
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

        // fragment'ımıza viewmodel bağlıyoruz
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        gameListRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            val decoration  = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapter = listingAdapter
        }
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[ListingViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            viewModel.getListDataFromAPI().collectLatest {
                listingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}

