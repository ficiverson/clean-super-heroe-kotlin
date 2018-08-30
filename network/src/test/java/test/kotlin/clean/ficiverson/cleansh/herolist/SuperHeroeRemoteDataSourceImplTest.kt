package test.kotlin.clean.ficiverson.cleansh.herolist

import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import test.kotlin.clean.ficiverson.network.heroelist.SuperHeroeRemoteDataSourceImpl
import test.kotlin.clean.ficiverson.network.injection.NetworkModules

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class SuperHeroeRemoteDataSourceImplTest {

    @Before
    fun before() {
        startKoin(listOf(NetworkModules("https://api.myjson.com/").module))
    }

    @After
    fun after() {
        closeKoin()
    }

    @Test
    fun `that we receive at least one superheroe`() {
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        Assertions.assertThat(superHeroeRemoteDataSource.getAll()?.size).isGreaterThan(0)
    }
}
