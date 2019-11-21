package br.com.unicubos.cats.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(ApiCatInterface::class.java)

}