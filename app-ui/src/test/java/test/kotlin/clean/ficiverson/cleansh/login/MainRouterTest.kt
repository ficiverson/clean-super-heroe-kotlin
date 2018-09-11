package test.kotlin.clean.ficiverson.cleansh.login

import android.content.Context
import android.content.Intent
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import test.kotlin.clean.ficiverson.cleansh.heroelist.SuperHeroesActivity
import java.lang.ref.WeakReference


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
        val intent = MainRouter(WeakReference(mockContext)).intentData()
        Assert.assertEquals(intent.component.className, Intent(mockContext, SuperHeroesActivity::class.java).component.className)
    }
}