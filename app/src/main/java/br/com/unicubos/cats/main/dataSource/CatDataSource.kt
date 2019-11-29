package br.com.unicubos.cats.main.dataSource

import androidx.paging.PageKeyedDataSource
import br.com.unicubos.cats.data.CatResponse
import br.com.unicubos.cats.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatDataSource : PageKeyedDataSource<Int, CatResponse>() {

    private var newPage = -1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CatResponse>
    ) {
        newPage++
        val call = ApiService().getService().handleGetCats(params.requestedLoadSize)
        call.enqueue(object : Callback<List<CatResponse>?>{
            override fun onFailure(call: Call<List<CatResponse>?>, t: Throwable) {}

            override fun onResponse(
                call: Call<List<CatResponse>?>,
                response: Response<List<CatResponse>?>
            ) {
                response.body()?.let {
                    callback.onResult(it, null, newPage)
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CatResponse>) {
        newPage++
        val call = ApiService().getService().handleGetCats(params.requestedLoadSize)
        call.enqueue(object : Callback<List<CatResponse>?>{
            override fun onFailure(call: Call<List<CatResponse>?>, t: Throwable) {}

            override fun onResponse(
                call: Call<List<CatResponse>?>,
                response: Response<List<CatResponse>?>
            ) {
                response.body()?.let {
                    callback.onResult(it, newPage)
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CatResponse>) {}

}