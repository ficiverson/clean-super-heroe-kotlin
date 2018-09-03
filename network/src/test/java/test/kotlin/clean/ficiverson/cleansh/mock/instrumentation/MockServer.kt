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
    private val mMockWebServer: MockWebServer

    init {
        mMockWebServer = MockWebServer()
    }

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
        enqueue(200, buffer.readUtf8())
        return this
    }

    fun enqueue(response: MockResponse): MockServer {
        mMockWebServer.enqueue(response)
        return this
    }

    @Throws(IOException::class)
    fun start(): String {
        mMockWebServer.start()
        val url = mMockWebServer.url("/").toString()
        return url.substring(0, url.length - 1)
    }


    @Throws(IOException::class)
    fun shutdown() {
        mMockWebServer.shutdown()
    }

    companion object {

        fun create(): MockServer {
            return MockServer()
        }
    }
}