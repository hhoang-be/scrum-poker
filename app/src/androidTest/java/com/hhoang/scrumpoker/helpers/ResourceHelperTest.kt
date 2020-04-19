package com.hhoang.scrumpoker.helpers

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hhoang.scrumpoker.model.SizingMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ResourceHelperTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        assertEquals("com.hhoang.scrumpoker", appContext.packageName)
    }

    @Test
    fun tShirtSizingResource() {
        val rh = ResourceHelper(appContext, SizingMode.T_SHIRT, "xxl")
        assertTrue(rh.drawableResourceId() > 0)
        assertEquals("Double Extra Large", rh.literalString())
    }

    @Test
    fun pokerSizingResource() {
        val rh = ResourceHelper(appContext, SizingMode.POKER_CARD, "01")
        assertTrue(rh.drawableResourceId() > 0)
        assertEquals("Low hanging fruit", rh.literalString())
    }
}
