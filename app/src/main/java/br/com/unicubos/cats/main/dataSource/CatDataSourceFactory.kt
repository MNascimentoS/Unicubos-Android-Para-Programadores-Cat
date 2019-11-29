package br.com.unicubos.cats.main.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.unicubos.cats.data.CatResponse

class CatDataSourceFactory : DataSource.Factory<Int, CatResponse>() {

    private var dataSource: CatDataSource? = null
    private var mutableLiveData: MutableLiveData<CatDataSource>? = null

    override fun create(): DataSource<Int, CatResponse> {
        dataSource = CatDataSource()
        mutableLiveData?.postValue(dataSource)
        return dataSource as CatDataSource
    }
}