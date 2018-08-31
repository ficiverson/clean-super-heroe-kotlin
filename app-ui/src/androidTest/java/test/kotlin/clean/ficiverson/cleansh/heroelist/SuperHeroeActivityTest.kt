package test.kotlin.clean.ficiverson.cleansh.heroelist;

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.loadKoinModules
import test.kotlin.clean.ficiverson.cache.heroelist.SuperHeroeLocalDataSourceImpl
import test.kotlin.clean.ficiverson.cleansh.injection.AppModules
import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.RepositoryInstrument.givenARepository
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeLocalDataSource
import test.kotlin.clean.ficiverson.data.datasource.heroelist.SuperHeroeRemoteDataSource
import test.kotlin.clean.ficiverson.interactor.heroeslist.GetSuperHeroesUseCase
import test.kotlin.clean.ficiverson.network.heroelist.SuperHeroeRemoteDataSourceImpl
import test.kotlin.clean.ficiverson.presentation.heroelist.SuperHeroesPresenter
import kotlin.clean.ficiverson.cleansh.R


@RunWith(AndroidJUnit4::class)
class SuperHeroeActivityTest {

    private val itemName = "Peter"

    @get: Rule
    val rule = object : ActivityTestRule<SuperHeroesActivity>(SuperHeroesActivity::class.java) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()

            val mockedRepositoryModule = applicationContext {
                factory { SuperHeroeRemoteDataSourceImpl() as SuperHeroeRemoteDataSource }
                factory { SuperHeroeLocalDataSourceImpl() as SuperHeroeLocalDataSource }
                factory { givenARepository(itemName) }
                factory { GetSuperHeroesUseCase(get()) }
                factory { SuperHeroesPresenter(it[AppModules.ACTIVITY_PARAM], get()) }
            }
            loadKoinModules(listOf(mockedRepositoryModule))
        }
    }

    @After
    fun after() {
        closeKoin()
    }

    @Test
    fun thatCanFetchSuperHeroesFromNetwork() {
        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.superHeroesRecyclerView, 2)
    }

    @Test
    fun thatTheNameOfTheHeroeIsCorrect() {
        onView(withId(R.id.superHeroesRecyclerView))
            .check(matches(atRecyclerViewPosition(1, hasDescendant(withText(itemName)))))
    }

    private fun atRecyclerViewPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}