package com.example.roomdatabaseexample.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.movie.ItemModel
import kotlinx.android.synthetic.main.priv_layout_horizontal.view.*

class MovieHorizontalAdapter(var context: Context, var itemModel: List<ItemModel>) :
    RecyclerView.Adapter<MovieHorizontalAdapter.MovieHorizontalViewHolder>() {
    class MovieHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemModel: ItemModel) {
            itemView.movieTitle.setText(itemModel.title)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHorizontalViewHolder {
        return MovieHorizontalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.priv_layout_horizontal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieHorizontalViewHolder, position: Int) {
        holder.bind(itemModel.get(position))
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/" + itemModel.get(position).poster_path)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemView.single_movie_progress.setVisibility(View.GONE)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemView.single_movie_progress.setVisibility(View.GONE)
                    return false
                }
            })
            .centerCrop()
            .into(holder.itemView.movieImg)
    }

    override fun getItemCount(): Int {
        return itemModel.size
    }
}