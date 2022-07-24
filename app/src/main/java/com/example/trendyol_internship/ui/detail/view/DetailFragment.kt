package com.example.trendyol_internship.ui.detail.view

import android.graphics.Color
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.trendyol_internship.R
import com.example.trendyol_internship.databinding.FragmentDetailBinding
import com.example.trendyol_internship.databinding.FragmentListingBinding
import com.example.trendyol_internship.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
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
        binding.cardViewDescription.setOnClickListener {
            if (gameDescription.lineCount == 4){
                gameDescription.setLines(20)
            }
            else{
                gameDescription.setLines(4)
            }
        }
        observeLiveData()

    }

    private fun observeLiveData() {
        val gameID: Int = args.id
        viewModel.updateGameDetail(gameID)
        viewModel.gameDetail.observe(viewLifecycleOwner, Observer {
            println("Name: ${viewModel.gameDetail.value?.name}")
            println("DescriptionRaw: ${viewModel.gameDetail.value?.descriptionRaw}")
            println("Metacritic: ${viewModel.gameDetail.value?.metaCritic}")
            if (viewModel.gameDetail.value?.genres?.size!! > 0){
                println("Genres: ${viewModel.gameDetail.value?.genres?.get(0)?.name}")
            }
            if (viewModel.gameDetail.value?.publishers?.size!! > 0){
                println("Publishers: ${viewModel.gameDetail.value?.publishers?.get(0)?.name}")
            }
            binding.apply {

                // render image
                Glide.with(binding.detailImageView).load(viewModel.gameDetail.value?.backgroundImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_search_24)
                    .into(binding.detailImageView)

                // edit game name
                binding.detailGameName.text = viewModel.gameDetail.value?.name

                // edit metacritic
                val metaCritic : Int = viewModel.gameDetail.value!!.metaCritic ?: -1
                binding.detailMetaCriticScore.text = metaCritic.toString()
                if (metaCritic != null){
                    if (metaCritic in (75..100)){
                        binding.detailMetaCriticScore.setTextColor(Color.GREEN)
                    }
                    else if (metaCritic in (50..74)){
                        binding.detailMetaCriticScore.setTextColor(Color.YELLOW)
                    }
                    else if (metaCritic in (0..49)){
                        binding.detailMetaCriticScore.setTextColor(Color.RED)
                    }
                    else{
                        binding.detailMetaCriticScore.visibility = View.GONE
                    }
                }

                // edit game description
                binding.gameDescription.text = viewModel.gameDetail.value?.descriptionRaw

            }

        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun expandDescription(view: View) {
        binding.cardViewDescription.gameDescription.setLines(10)
    }


}