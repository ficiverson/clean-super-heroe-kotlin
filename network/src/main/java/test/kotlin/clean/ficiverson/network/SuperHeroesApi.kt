package test.kotlin.clean.ficiverson.network

import retrofit2.Call
import retrofit2.http.GET
import test.kotlin.clean.ficiverson.network.model.SuperHeroeWrapperResponse


interface SuperHeroesApi {
    @GET("bins/bvyob")
    fun getHeroes(): Call<SuperHeroeWrapperResponse>
}