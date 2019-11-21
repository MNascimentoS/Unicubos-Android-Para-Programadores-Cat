package br.com.unicubos.cats.details.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.unicubos.cats.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val ATTR_IMAGE = "image"
        const val TRANSITION_IMAGE = "image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        intent?.getStringExtra(ATTR_IMAGE)?.let {
            Picasso.get().load(it).into(catIMG)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }
}
