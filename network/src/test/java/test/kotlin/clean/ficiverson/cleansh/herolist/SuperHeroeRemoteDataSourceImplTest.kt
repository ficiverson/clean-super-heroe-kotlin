package test.kotlin.clean.ficiverson.cleansh.herolist

import com.karumi.kotlinsnapshot.matchWithSnapshot
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import test.kotlin.clean.ficiverson.cleansh.mock.fixtures.ServerFixtures.HEROES_LIST
import test.kotlin.clean.ficiverson.cleansh.mock.fixtures.ServerFixtures.enqueueServerError
import test.kotlin.clean.ficiverson.cleansh.mock.fixtures.ServerFixtures.enqueueServerFile
import test.kotlin.clean.ficiverson.cleansh.mock.instrumentation.MockServer
import test.kotlin.clean.ficiverson.network.heroelist.SuperHeroeRemoteDataSourceImpl
import test.kotlin.clean.ficiverson.network.injection.NetworkModules


/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class SuperHeroeRemoteDataSourceImplTest {

    lateinit var mockServer: MockServer

    @Before
    fun before() {
        mockServer = MockServer.create()
        startKoin(listOf(NetworkModules(mockServer.start()).module))
    }

    @After
    fun after() {
        mockServer.shutdown()
        closeKoin()
    }

    @Test
    fun `that we receive at least one superheroe`() {
        enqueueServerFile(mockServer, HEROES_LIST)
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        Assertions.assertThat(superHeroeRemoteDataSource.getAll()?.size).isGreaterThan(0)
    }

    @Test
    fun `that we receive at least one superheroe match snapshot`() {
        enqueueServerFile(mockServer, HEROES_LIST)
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        superHeroeRemoteDataSource.getAll()?.matchWithSnapshot("should fetch the data from the network")
    }

    @Test(expected = Exception::class)
    fun `that cannot fetch  heroes`() {
        enqueueServerError(mockServer, 500)
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        superHeroeRemoteDataSource.getAll()
    }
}
