package br.com.unicubos.cats.main.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.unicubos.cats.R
import br.com.unicubos.cats.details.ui.DetailsActivity
import br.com.unicubos.cats.details.ui.DetailsActivity.Companion.ATTR_IMAGE
import br.com.unicubos.cats.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this)[MainViewModel::class.java]
    }
    private lateinit var adapter: CatRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        initObservables()
        viewModel.getCats()
    }

    private fun initUi() {
        adapter = CatRecyclerAdapter { cat, view ->
            val options = ActivityOptions
                .makeSceneTransitionAnimation(this, view, DetailsActivity.TRANSITION_IMAGE)

            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtra(ATTR_IMAGE, cat.url)
            }, options.toBundle())
        }
        recyclerView?.layoutManager = GridLayoutManager(this, 2)
        recyclerView?.adapter = adapter
    }

    private fun initObservables() {
        viewModel.catList.observe(this, Observer { list ->
            adapter.addItems(list)
        })
    }

}