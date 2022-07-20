package com.example.trendyol_internship.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trendyol_internship.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.flow.collectLatest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PARAM_SEARCH_KEYWORD = "keyword"

class SearchFragment : Fragment() {
    private lateinit var searchKeyword: String
    private var searchAdapter = SearchAdapter()

    private var _binding: FragmentSearchBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                searchKeyword = it.getString(PARAM_SEARCH_KEYWORD).toString()
                println("SEARCH PARAMETER: $searchKeyword")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchParameter.text = searchKeyword
        return binding.root
        //return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment'ımıza viewmodel bağlıyoruz
        //initRecyclerView()
        //initViewModel()
        //observeLiveData()
    }

    private fun initRecyclerView() {
        gameListRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            val decoration  = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapter = searchAdapter
        }
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[SearchViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            viewModel.getSearchResultFromAPI().collectLatest {
                searchAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}