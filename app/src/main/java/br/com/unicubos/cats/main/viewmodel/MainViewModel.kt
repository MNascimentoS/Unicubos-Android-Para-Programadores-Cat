package br.com.unicubos.cats.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.unicubos.cats.data.CatResponse
import br.com.unicubos.cats.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val catListResponse = MutableLiveData<List<CatResponse>>()

    val catList: LiveData<List<CatResponse>> = catListResponse

    fun getCats() {
        val call = ApiService().getService().handleGetCats()
        call.enqueue(object : Callback<List<CatResponse>?> {
            override fun onFailure(call: Call<List<CatResponse>?>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<CatResponse>?>,
                response: Response<List<CatResponse>?>
            ) {
                catListResponse.value = response.body()
            }
        })
    }

}