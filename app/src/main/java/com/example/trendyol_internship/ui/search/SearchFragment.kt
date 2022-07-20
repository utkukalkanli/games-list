package com.example.trendyol_internship.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trendyol_internship.databinding.FragmentSearchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val SEARCH_KEYWORD = "keyword"

class SearchFragment : Fragment() {
    private lateinit var searchKeyword: String

    private var _binding: FragmentSearchBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                searchKeyword = it.getString(SEARCH_KEYWORD).toString()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}