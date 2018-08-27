package test.kotlin.clean.ficiverson.cleansh.mapper

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.SuperHeroeIntrument.Companion.givenASuperHeroeEntity
import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.SuperHeroeIntrument.Companion.givenASuperHeroeRemote
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.network.mapper.SuperHeroeEntityMapper

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeEntityMapperTest {

    @Test
    fun `that can map a network response to entity`() {
        val mappedInstance: Any = SuperHeroeEntityMapper().mapFromRemote(givenASuperHeroeRemote())
        assertThat(mappedInstance is SuperHeroeEntity).isTrue()
    }

    @Test
    fun `that can map a network response to entity with same name`() {
        val mappedInstance = SuperHeroeEntityMapper().mapFromRemote(givenASuperHeroeRemote())
        assertThat(mappedInstance).isNotNull();
        assertThat(givenASuperHeroeEntity().name).isEqualTo(mappedInstance.name)
    }
}
