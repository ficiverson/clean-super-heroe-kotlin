package test.kotlin.clean.ficiverson.network.injection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.kotlin.clean.ficiverson.network.SuperHeroesApi
import java.util.concurrent.TimeUnit

class NetworkModules(private val baseUrl: String) {

    companion object {
        private const val OK_HTTP_TIMEOUT = 30L
    }

    val networkModule = module {
        single { okHttpClient() }
        single { retrofit(get()) }
        single { superHeroesApi(get()) }
    }

    private fun okHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message -> System.out.println("Interceptor $message") }
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private fun retrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun superHeroesApi(retrofit: Retrofit) =
        retrofit.create(SuperHeroesApi::class.java)
}
