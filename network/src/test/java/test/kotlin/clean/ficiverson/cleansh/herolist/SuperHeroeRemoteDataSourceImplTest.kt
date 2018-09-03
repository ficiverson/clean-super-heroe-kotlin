package test.kotlin.clean.ficiverson.cleansh.herolist

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

    lateinit var mMockServer: MockServer

    @Before
    fun before() {
        mMockServer = MockServer.create();
        startKoin(listOf(NetworkModules(mMockServer.start()).module))
    }

    @After
    fun after() {
        mMockServer.shutdown()
        closeKoin()
    }

    @Test
    fun `that we receive at least one superheroe`() {
        enqueueServerFile(mMockServer, HEROES_LIST);
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        Assertions.assertThat(superHeroeRemoteDataSource.getAll()?.size).isGreaterThan(0)
    }

    @Test(expected = Exception::class)
    fun `that cannot fetch  heroes`() {
        enqueueServerError(mMockServer, 500);
        val superHeroeRemoteDataSource = SuperHeroeRemoteDataSourceImpl()
        val result = superHeroeRemoteDataSource.getAll();
    }
}
