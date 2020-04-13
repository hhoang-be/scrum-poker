package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.hhoang.scrumpoker.model.ScrumPokerViewModel
import com.hhoang.scrumpoker.model.SizingMode
import com.hhoang.scrumpoker.model.ViewMode
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private var lazyViewModel = viewModels<ScrumPokerViewModel>()
    private lateinit var viewModel: ScrumPokerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.app_name, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener { m -> navigationItemSelected(m) }
        darkThemSwichChanged(navigationView)
        viewModel = lazyViewModel.value
    }

    private fun darkThemSwichChanged(navigationView: NavigationView) {
        val menuItem = navigationView.menu.findItem(R.id.miDarkTheme)
        val darkThemeSwitch = menuItem.actionView.findViewById<Switch>(R.id.miDarkThemeSwitch)
        darkThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            drawerLayout.closeDrawer(GravityCompat.START)
            viewModel.darkThemeMode.value = isChecked
            if (isChecked) {
                findViewById<ConstraintLayout>(R.id.mainActivityLayout).setBackgroundResource(R.color.colorDarkMode)
            } else {
                findViewById<ConstraintLayout>(R.id.mainActivityLayout).setBackgroundResource(R.color.colorLightMode)
            }
        }

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
