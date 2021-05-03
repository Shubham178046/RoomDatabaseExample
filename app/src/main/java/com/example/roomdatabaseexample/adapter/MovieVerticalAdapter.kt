package com.example.roomdatabaseexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.movie.CategoryModel
import kotlinx.android.synthetic.main.priv_layout_vertical.view.*

class MovieVerticalAdapter(var context: Context, var categoryList: List<CategoryModel>) :
    RecyclerView.Adapter<MovieVerticalAdapter.MovieVerticalViewHolder>() {
    class MovieVerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemModel: CategoryModel) {
            itemView.categoryText.setText(itemModel.categoryTitle)
            val adapter = MovieHorizontalAdapter(itemView.context, itemModel.movieList!!)
            itemView.horizontalRecyclerview.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.horizontalRecyclerview.adapter = adapter

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVerticalViewHolder {
        return MovieVerticalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.priv_layout_vertical, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieVerticalViewHolder, position: Int) {
        holder.bind(categoryList.get(position))
        if (position == categoryList.size - 1) holder.itemView.Linear.setPadding(
            0,
            0,
            0,
            130
        ) else holder.itemView.Linear.setPadding(0, 0, 0, 0)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}