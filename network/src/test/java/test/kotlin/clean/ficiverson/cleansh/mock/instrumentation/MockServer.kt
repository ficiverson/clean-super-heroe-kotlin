package test.kotlin.clean.ficiverson.cleansh.mock.instrumentation

import java.io.IOException;
import java.io.InputStream;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;


/**
 * Created by f.souto.gonzalez on 03/09/2018.
 */
class MockServer private constructor() {

    companion object {
        fun create(): MockServer {
            return MockServer()
        }
    }

    private val mockWebServer: MockWebServer = MockWebServer()

    fun enqueue(code: Int): MockServer {
        val response = MockResponse().setResponseCode(code)
        enqueue(response)
        return this
    }

    fun enqueue(code: Int, body: String): MockServer {
        val response = MockResponse()
            .setResponseCode(code)
            .setBody(body)
        enqueue(response)
        return this
    }

    @Throws(IOException::class)
    fun enqueueFile(fileName: String): MockServer {
        enqueueFile(200, fileName)
        return this
    }

    @Throws(IOException::class)
    fun enqueueFile(code: Int, fileName: String): MockServer {
        val `in` = this.javaClass.classLoader.getResourceAsStream(fileName)
        val buffer = Okio.buffer(Okio.source(`in`))
        enqueue(code, buffer.readUtf8())
        return this
    }

    fun enqueue(response: MockResponse): MockServer {
        mockWebServer.enqueue(response)
        return this
    }

    @Throws(IOException::class)
    fun start(): String {
        mockWebServer.start()
        return mockWebServer.url("/").toString()
    }

    @Throws(IOException::class)
    fun shutdown() {
        mockWebServer.shutdown()
    }
}