package test.kotlin.clean.ficiverson.cache.herolist

import org.assertj.core.api.Assertions
import org.junit.Test
import test.kotlin.clean.ficiverson.cache.heroelist.SuperHeroeLocalDataSourceImpl

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class SuperHeroeLocalDataSourceImplTest {

    @Test
    fun `that can handle a remote request`() {
        val superHeroeRemoteDataSource = SuperHeroeLocalDataSourceImpl()
        Assertions.assertThat(superHeroeRemoteDataSource.getAll()?.size).isEqualTo(0)
    }
}
