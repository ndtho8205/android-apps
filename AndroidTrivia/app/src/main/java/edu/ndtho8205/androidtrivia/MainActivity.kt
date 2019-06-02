package edu.ndtho8205.androidtrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import edu.ndtho8205.androidtrivia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
//    private lateinit var mAppBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        mDrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.fragment_nav_host)
        NavigationUI.setupActionBarWithNavController(this, navController, mDrawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == controller.graph.startDestination) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
//        mAppBarConfig = AppBarConfiguration(navController.graph, mDrawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_nav_host)
        return NavigationUI.navigateUp(navController, mDrawerLayout)
    }
}
