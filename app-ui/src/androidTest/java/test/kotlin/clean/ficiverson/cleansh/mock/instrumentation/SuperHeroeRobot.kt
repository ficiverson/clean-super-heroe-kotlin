package test.kotlin.clean.ficiverson.cleansh.mock.instrumentation

import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.interaction.BaristaClickInteractions
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions

import kotlin.clean.ficiverson.cleansh.R

/**
 * Created by f.souto.gonzalez on 30/08/2018.
 */
class SuperHeroeRobot {
    fun email(email: String) {
        BaristaEditTextInteractions.writeTo(R.id.loginEmail, email)
    }

    fun password(password: String) {
        BaristaEditTextInteractions.writeTo(R.id.loginEmail, password)
    }

    infix fun submit(func: SuperHeroeRobotResult.() -> Unit): SuperHeroeRobotResult {
        BaristaClickInteractions.clickOn(R.id.loginSubmitButton)
        return SuperHeroeRobotResult().apply { func() }
    }
}

class SuperHeroeRobotResult {
    fun isSuccess() {
        BaristaVisibilityAssertions.assertDisplayed(R.id.superHeroesRecyclerView)
    }
}