package test.kotlin.clean.ficiverson.cleansh.herolist

import org.assertj.core.api.Assertions
import org.junit.Test
import test.kotlin.clean.ficiverson.network.heroelist.SuperHeroeRemoteDataSourceImpl

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class SuperHeroeRemoteDataSourceImplTest {

    @Test
    fun `that can handle a remote request`() {
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        Assertions.assertThat(superHeroeRemoteDataSource.getAll()?.size).isEqualTo(0)
    }
}
