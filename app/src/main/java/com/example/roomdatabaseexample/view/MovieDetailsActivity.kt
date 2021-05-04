package com.example.roomdatabaseexample.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.movie.ItemModel
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    var itemModel: ItemModel = ItemModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        if (intent.hasExtra("item_model")) {
            if (intent.getSerializableExtra("item_model") != null) {
                itemModel = intent.getSerializableExtra("item_model") as ItemModel
            }
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + itemModel.poster_path)
                .into(movieFocusImg)
            FocusTitle.setText(itemModel.title)
            FocusDate.setText(itemModel.release_date)
            FocusLanguage.setText(itemModel.original_language)
            FocusImdb.setText(itemModel.vote_average.toString())
            OverView.setText(itemModel.overview)

        }
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }
}