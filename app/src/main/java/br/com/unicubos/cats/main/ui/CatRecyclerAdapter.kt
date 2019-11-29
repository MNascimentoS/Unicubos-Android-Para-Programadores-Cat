package br.com.unicubos.cats.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.unicubos.cats.R
import br.com.unicubos.cats.data.CatResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cat_card.view.*

class CatRecyclerAdapter(private val onClick: ((CatResponse, View?) -> Unit)) :
    PagedListAdapter<CatResponse, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<CatResponse>() {
            override fun areItemsTheSame(oldItem: CatResponse, newItem: CatResponse): Boolean =
                (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: CatResponse, newItem: CatResponse): Boolean =
                (oldItem == newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cat_card, parent,
                false
            )
        ) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            getItem(position)?.let { cat ->
                Picasso.get().load(cat.url).into(catIMG)
                if (!cat.categories.isNullOrEmpty()) {
                    catDataTXT?.text = cat.categories.first().name
                }
                setOnClickListener {
                    onClick(cat, cardView)
                }
            }
        }
    }
}