package test.kotlin.clean.ficiverson.cleansh.mock.instrumentation

import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions
import com.schibsted.spain.barista.interaction.BaristaClickInteractions
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions
import com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard
import kotlin.clean.ficiverson.cleansh.R

/**
 * Created by f.souto.gonzalez on 30/08/2018.
 */
class SuperHeroeRobotList {
    fun searchText(searchText: String) {
        BaristaEditTextInteractions.writeTo(R.id.action_search, searchText)
    }

    fun search() {
        BaristaClickInteractions.clickOn(R.id.action_search)
    }

    infix fun hideKeyboard(func: SuperHeroeRobotListResult.() -> Unit): SuperHeroeRobotListResult {
        closeKeyboard()
        return SuperHeroeRobotListResult().apply { func() }
    }
}

class SuperHeroeRobotListResult {
    fun isSuccess() {
        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.superHeroesRecyclerView, 2)
    }
}