package com.example.trendyol_internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
//import androidx.appcompat.widget.SearchView
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.trendyol_internship.databinding.ActivityMainBinding
import com.example.trendyol_internship.ui.listing.view.ListingFragmentDirections


class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationController = Navigation.findNavController(this,R.id.fragment)
        //navigationController = Navigation.findNavController(this,R.id.fragment) // toolbar'a backbutton koyuyor
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    println("KEY WORD: $query")
                    binding.searchView.clearFocus()
                    val bundle = bundleOf("keyword" to query)
                    navigationController.navigate(R.id.searchFragment, bundle)
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.equals("")){
                        println("QUERY EMPTY !!!")
                        navigationController.navigate(R.id.listingFragment)
                    }
                    return false
                }

            }
        )
    }



    // kullanıcı uygulama içerisinde baska bi tarafa giderse cagırılıyor

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController, null)
    }

}