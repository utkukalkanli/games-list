package com.example.trendyol_internship.ui.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.trendyol_internship.R
import com.example.trendyol_internship.databinding.FragmentDetailBinding
import com.example.trendyol_internship.databinding.FragmentListingBinding
import com.example.trendyol_internship.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>() // auto-generated class

    /**
     * When we use view binding in the fragment we have to pay special attention because the view of a fragment can be destroyed while the
     * fragment itself is still in memory. If this is the case we have to null out our binding variable otherwise it will keep
     * unnecessary reference to the whole view hierarchy which is a memory leak.
     */
    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val gameID: Int
        binding.apply {
            gameID = args.id
            println("DETAIL FRAGMENT: $gameID")
        }

        println("FRAGMENT: " + viewModel.getGameDetail(gameID)?.name)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


}