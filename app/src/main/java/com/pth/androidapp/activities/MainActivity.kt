package com.pth.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.pth.androidapp.R
import com.pth.androidapp.base.activities.BaseActivity
import com.pth.androidapp.base.components.CustomBottomNavigation
import com.pth.androidapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val fragmentsToShowHeader = mapOf(
        R.id.loginFragment to "Login",
        R.id.registerFragment to "Create Account",
        R.id.forgotPasswordFragment to "Forgot Password"
    )

    private val fragmentsToShowBottomNav = setOf(
        R.id.homeFragment,
        R.id.wishlistFragment,
        R.id.notificationFragment,
        R.id.profileFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets(binding)
        setupNavigation()
        setupUI()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


    }

    private fun setupUI(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            setupHeader(destination)
            setupBottomNavigation(destination)
        }
    }

    private fun setupHeader(destination: NavDestination) {

        fragmentsToShowHeader[destination.id]?.let { title ->
            binding.header.visibility = View.VISIBLE
            binding.headerTitle.text = title
            binding.backButton.setOnClickListener { navController.popBackStack() }
        } ?: run {
            binding.header.visibility = View.GONE
        }
    }

    private fun setupBottomNavigation(destination: NavDestination) {
        val bottomNav = findViewById<CustomBottomNavigation>(R.id.bottomNav)
        if (destination.id in fragmentsToShowBottomNav) {
            bottomNav.visibility = View.VISIBLE
            bottomNav.updateSelectedItem(
                when (destination.id) {
                    R.id.homeFragment -> R.id.home_nav
                    R.id.wishlistFragment -> R.id.wishlist_nav
                    R.id.notificationFragment -> R.id.notification_nav
                    R.id.profileFragment -> R.id.profile_nav
                    else -> R.id.home_nav
                }
            )

            bottomNav.setItemClickListener { itemId ->
                when (itemId) {
                    R.id.home_nav -> navController.navigate(R.id.homeFragment)
                    R.id.wishlist_nav -> navController.navigate(R.id.wishlistFragment)
                    R.id.notification_nav -> navController.navigate(R.id.notificationFragment)
                    R.id.profile_nav -> navController.navigate(R.id.profileFragment)
                }
            }
        } else {
            bottomNav.visibility = View.GONE
        }

    }
}
