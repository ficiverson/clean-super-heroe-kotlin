package test.kotlin.clean.ficiverson.cleansh.login

import android.content.Context
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import test.kotlin.clean.ficiverson.cleansh.navigator.IntentData.Companion.BASE_ROUTE_APP
import test.kotlin.clean.ficiverson.cleansh.navigator.NoParams


/**
 * Created by f.souto.gonzalez on 11/09/2018.
 */
@RunWith(AndroidJUnit4::class)
class MainRouterTest {

    lateinit var mockContext: Context

    @Before
    fun before() {
        mockContext = RuntimeEnvironment.application
    }

    @Test
    fun `that can calculate next screen`() {
        val intents = MainRouter(mockContext).intentData(NoParams(), MainRouter.HEROE_LIST)
        Assert.assertEquals(intents!![0].data.toString(), BASE_ROUTE_APP + MainRouter.HEROE_LIST)
    }
}