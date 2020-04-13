package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.hhoang.scrumpoker.model.ScrumPokerViewModel
import com.hhoang.scrumpoker.model.SizingMode
import com.hhoang.scrumpoker.model.ViewMode
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private var lazyViewModel = viewModels<ScrumPokerViewModel>()
    private lateinit var viewModel: ScrumPokerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.app_name, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener { m -> navigationItemSelected(m) }
        viewModel = lazyViewModel.value
    }

    private fun navigationItemSelected(menuItem: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (menuItem.itemId) {
            R.id.miGridView -> viewModel.setViewMode(ViewMode.GRID)
            R.id.miScrollView -> viewModel.setViewMode(ViewMode.SWIPE)
            R.id.miPokerCard -> viewModel.setSizingMode(SizingMode.POKER_CARD)
            R.id.miTshirtCard -> viewModel.setSizingMode(SizingMode.T_SHIRT)
        }
        return true
    }
}
