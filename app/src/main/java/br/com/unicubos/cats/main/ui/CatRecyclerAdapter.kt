package br.com.unicubos.cats.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.unicubos.cats.R
import br.com.unicubos.cats.data.CatResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cat_card.view.*

class CatRecyclerAdapter(private val onClick: ((CatResponse, View?) -> Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var catList = arrayListOf<CatResponse>()

    fun addItems(list: List<CatResponse>) {
        catList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cat_card, parent,
                false
            )
        ) {}

    override fun getItemCount(): Int = catList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            val cat = catList[position]
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