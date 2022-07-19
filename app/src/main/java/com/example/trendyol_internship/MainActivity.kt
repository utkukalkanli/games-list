package com.example.trendyol_internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import com.example.trendyol_internship.ui.listing.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navigationController)


    }

    // kullanıcı uygulama içerisinde baska bi tarafa giderse cagırılıyor

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController, null)
    }

}