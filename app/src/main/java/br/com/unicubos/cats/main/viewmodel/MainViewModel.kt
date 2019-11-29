package br.com.unicubos.cats.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.unicubos.cats.data.CatResponse
import br.com.unicubos.cats.main.dataSource.CatDataSourceFactory
import br.com.unicubos.cats.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isLoading.value = true
    }

    val catList: LiveData<PagedList<CatResponse>> =
        LivePagedListBuilder<Int, CatResponse>(CatDataSourceFactory(), 10)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<CatResponse>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    isLoading.value = false
                }

                override fun onItemAtFrontLoaded(itemAtFront: CatResponse) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    isLoading.value = false
                }

                override fun onItemAtEndLoaded(itemAtEnd: CatResponse) {
                    super.onItemAtEndLoaded(itemAtEnd)
                    isLoading.value = false
                }
            })
            .build()


}