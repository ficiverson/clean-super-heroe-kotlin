package test.kotlin.clean.ficiverson.cleansh

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.clean.ficiverson.cleansh.R

@RunWith(AndroidJUnit4::class)
class SuperHeroeTest {

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

class SuperHeroeRobot {
    fun email(email: String) {
        writeTo(R.id.loginEmail, email)
    }

    fun password(password: String) {
        writeTo(R.id.loginEmail, password)
    }

    infix fun submit(func: SuperHeroeRobotResult.() -> Unit): SuperHeroeRobotResult {
        clickOn(R.id.loginSubmitButton)
        return SuperHeroeRobotResult().apply { func() }
    }
}

class SuperHeroeRobotResult {
    fun isSuccess() {
        assertDisplayed(R.id.superHeroesTitle)
    }
}