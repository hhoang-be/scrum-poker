package com.hhoang.scrumpoker

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentNavigationIT: TestBase() {

    @Test
    fun layoutModeNavigation() {
        clickOnNavigationMenu(R.id.miScrollView)
        onView(withId(R.id.swipeRecycleView))
            .check(matches(isDisplayed()))

        clickOnNavigationMenu(R.id.miGridView)
        onView(withId(R.id.gridStartFrame))
            .check(matches(isDisplayed()))
    }

    @Test
    fun sizingModeNavigation() {
        clickOnNavigationMenu(R.id.miTshirtCard)
        onView(withId(R.id.viewGridTshirtStart))
            .check(matches(isDisplayed()))

        clickOnNavigationMenu(R.id.miPokerCard)
        onView(withId(R.id.tblPokerGridLayout))
            .check(matches(isDisplayed()))
    }

    private fun clickOnNavigationMenu(menuId: Int) {
        onView(withId(R.id.drawer_layout))
            .check(matches(DrawerMatchers.isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(menuId))
    }
}