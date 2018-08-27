package test.kotlin.clean.ficiverson.cache.mapper

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import test.kotlin.clean.ficiverson.cache.mock.instrumentation.SuperHeroeIntrument.Companion.givenASuperHeroeEntity
import test.kotlin.clean.ficiverson.cache.mock.instrumentation.SuperHeroeIntrument.Companion.givenASuperHeroeLocal
import test.kotlin.clean.ficiverson.data.model.SuperHeroeEntity
import test.kotlin.clean.ficiverson.cache.mapper.SuperHeroeEntityMapper

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */
class SuperHeroeEntityMapperTest {

    @Test
    fun `that can map a network response to entity`() {
        val mappedInstance: Any = SuperHeroeEntityMapper().mapFromDatabase(givenASuperHeroeLocal())
        assertThat(mappedInstance is SuperHeroeEntity).isTrue()
    }

    @Test
    fun `that can map a network response to entity with same name`() {
        val mappedInstance = SuperHeroeEntityMapper().mapFromDatabase(givenASuperHeroeLocal())
        assertThat(mappedInstance).isNotNull();
        assertThat(givenASuperHeroeEntity().name).isEqualTo(mappedInstance.name)
    }
}
