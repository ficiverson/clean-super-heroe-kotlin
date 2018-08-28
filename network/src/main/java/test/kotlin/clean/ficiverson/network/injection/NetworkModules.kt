package test.kotlin.clean.ficiverson.network.injection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModules {

    val module = applicationContext {
        bean { okHttpClient() }
        bean { retrofit(get()) }
    }

//        private fun stationApi(retrofit: Retrofit): StationApi =
//        retrofit.create(StationApi::class.java)

    private fun retrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("http://superheroapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun okHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message -> System.out.println("Interceptor $message") }.apply { level = HttpLoggingInterceptor.Level.BODY })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
}
