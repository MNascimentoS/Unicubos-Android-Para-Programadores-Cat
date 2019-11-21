package br.com.unicubos.cats.services

import br.com.unicubos.cats.data.CatResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCatInterface {

    companion object {
        private const val API_KEY_QUERY = "api_key"
        private const val API_QUERY_VALUE = "26e9a3c8-da9a-4849-b853-b0b560949516"
    }

    @GET("v1/images/search")
    fun handleGetCats(@Query("limit") limit: Int = 20,
                      @Query("size") size: String = "full",
                      @Query(API_KEY_QUERY) api_key: String = API_QUERY_VALUE) : Call<List<CatResponse>?>

}