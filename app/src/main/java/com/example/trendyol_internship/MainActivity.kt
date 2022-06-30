package com.example.trendyol_internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trendyol_internship.list.adapter.ListGameAdapter
import com.example.trendyol_internship.databinding.ActivityMainBinding
import com.example.trendyol_internship.list.viewmodel.ListGameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gameAdapter: ListGameAdapter
    private val viewModel: ListGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRecyclerView()
        loadData()
    }

    private fun setupRecyclerView() {

        gameAdapter = ListGameAdapter()

        binding.recyclerView.apply {
            adapter = gameAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }

    }

    private fun loadData() {

        lifecycleScope.launch {
            viewModel.listData.collect {

                Log.d("aaa", "load: $it")
                gameAdapter.submitData(it)
            }

        }
    }
}