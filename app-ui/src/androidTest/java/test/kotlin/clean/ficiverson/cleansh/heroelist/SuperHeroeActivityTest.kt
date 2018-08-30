package test.kotlin.clean.ficiverson.cleansh.heroelist;

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.clean.ficiverson.cleansh.R

@RunWith(AndroidJUnit4::class)
class SuperHeroeActivityTest {

    @get: Rule
    var mActivityRule = ActivityTestRule(SuperHeroesActivity::class.java)

    @Test
    fun thatCanFetchSuperHeroesFromNetwork() {
        BaristaVisibilityAssertions.assertDisplayed(R.id.superHeroesTitle)
        BaristaVisibilityAssertions.assertContains(R.id.superHeroesTitle, "size")
    }
}



