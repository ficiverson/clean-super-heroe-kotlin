package test.kotlin.clean.ficiverson.cleansh.login;

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.SuperHeroeRobot

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun thatOpensANewScreen() {
        login {
            email("myemail@test.com")
            password("password")
        } submit {
            isSuccess()
        }
    }

    private fun login(func: SuperHeroeRobot.() -> Unit) = SuperHeroeRobot().apply { func() }
}



