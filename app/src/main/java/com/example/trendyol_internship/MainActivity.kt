package com.example.trendyol_internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
//import androidx.appcompat.widget.SearchView
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.trendyol_internship.databinding.ActivityMainBinding
import com.example.trendyol_internship.ui.listing.view.ListingFragment
import com.example.trendyol_internship.ui.listing.view.ListingFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(
            this,
            navigationController
        ) // this adds back button
    }

    // kullanıcı uygulama içerisinde baska bi tarafa giderse cagırılıyor
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController, null)
    }



}