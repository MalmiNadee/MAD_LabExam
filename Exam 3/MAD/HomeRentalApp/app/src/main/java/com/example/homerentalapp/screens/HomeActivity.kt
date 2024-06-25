package com.example.homerentalapp.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.homerentalapp.R
import com.example.homerentalapp.fragment.AccountFragment
import com.example.homerentalapp.fragment.MenuFragment
import com.example.homerentalapp.fragment.FavoriteFragment
import com.example.homerentalapp.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var  bottomNavigationView: BottomNavigationView
    private lateinit var  card1 :CardView
    private lateinit var  card2: CardView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nav)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener(this@HomeActivity)
        loadFragment(HomeFragment())

        card1 = findViewById(R.id.cardView1)
        card1.setOnClickListener{
            startActivity( Intent(this@HomeActivity, DetailsActivity::class.java) )
            finish()
        }

        card2 = findViewById(R.id.cardView2)
        card2.setOnClickListener{
            startActivity( Intent(this@HomeActivity, DetailsActivity::class.java) )
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


   override fun onNavigationItemSelected(item: MenuItem) : Boolean {

            val fragment = when(item.itemId){
                R.id.menu_home -> HomeFragment()
                R.id.menu_favorite -> FavoriteFragment()
                R.id.menu_menu_ic -> MenuFragment()
                R.id.menu_account -> AccountFragment()
                else -> null


            }
             return loadFragment(fragment)
        }




    private fun loadFragment(fragment : Fragment?) : Boolean{

            fragment?.let{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,fragment).commit()
                return true
            }

        return false
    }
}