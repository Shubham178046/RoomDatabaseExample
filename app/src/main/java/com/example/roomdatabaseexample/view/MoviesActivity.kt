package com.example.roomdatabaseexample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.adapter.MovieVerticalAdapter
import com.example.roomdatabaseexample.model.movie.CategoryModel
import com.example.roomdatabaseexample.model.movie.ItemModel
import com.example.roomdatabaseexample.util.Status
import com.example.roomdatabaseexample.util.Utils
import com.example.roomdatabaseexample.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movies.*
import java.util.*

class MoviesActivity : AppCompatActivity() {
    var adapter: MovieVerticalAdapter? = null
    var moviesViewModel: MoviesViewModel? = null
    var categoryLists: ArrayList<CategoryModel> = ArrayList<CategoryModel>()
    var AllMovies: ArrayList<ItemModel> = ArrayList<ItemModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        verticalRecyclerView.layoutManager = LinearLayoutManager(this)
        if (Utils.getConnectivityStatus(this) == 0 && Utils.getConnectivityStatusString(
                this
            ) == Utils.NOT_CONNECT
        ) {

            moviesViewModel!!.getMovieData(this).observe(this, Observer {
                AllMovies.clear()
                categoryLists.clear()
                it.items?.let { it1 -> AllMovies.addAll(it1) }
                for (i in 0 until AllMovies.size) {
                    val temporaryList: ArrayList<ItemModel> = ArrayList<ItemModel>()
                    val item: ItemModel = AllMovies.get(i);
                    if (!categoryLists.isEmpty()) {
                        var check = false
                        for (j in categoryLists.indices) {
                            if (categoryLists[j].IMDB === item.vote_average!!.toInt()) {
                                categoryLists.get(j).movieList!!.add(item)
                                check = true
                                break
                            }
                        }
                        if (check == false) {
                            temporaryList.clear()
                            temporaryList.add(item)
                            var categoryModel = CategoryModel()
                            categoryModel.categoryID = AllMovies.get(i).id
                            categoryModel.IMDB =
                                AllMovies.get(i).vote_average!!.toInt()
                            categoryModel.categoryTitle =
                                "IMDB" + AllMovies.get(i).vote_average!!.toInt()
                            categoryModel.movieList = temporaryList
                            categoryLists.add(categoryModel)
                            temporaryList.clear()
                        }
                    } else {
                        temporaryList.clear()
                        temporaryList.add(item)
                        var categoryModel = CategoryModel()
                        categoryModel.categoryID = item.id
                        categoryModel.IMDB = item.vote_average!!.toInt()
                        categoryModel.categoryTitle =
                            "IMDB" + item.vote_average!!.toInt()
                        categoryModel.movieList = temporaryList
                        categoryLists.add(categoryModel)
                        temporaryList.clear()
                    }
                }
                sortList(categoryLists)
                categoryLists.remove(categoryLists[categoryLists.size - 1])
                Collections.reverse(categoryLists)
                if (categoryLists != null) {
                    if (categoryLists.size > 0) {
                        adapter = MovieVerticalAdapter(this, categoryLists)
                    }
                }

                verticalRecyclerView.adapter = adapter
            })
        } else {
            moviesViewModel!!.getMovieData()?.observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let {
                                AllMovies.clear()
                                categoryLists.clear()
                                it.items?.let { it1 -> AllMovies.addAll(it1) }

                                if (it != null) {
                                    moviesViewModel!!.insertData(this, it)
                                }
                                //  temporaryList.clear()
                                for (i in 0 until AllMovies.size) {
                                    val temporaryList: ArrayList<ItemModel> = ArrayList<ItemModel>()
                                    val item: ItemModel = AllMovies.get(i);
                                    if (!categoryLists.isEmpty()) {
                                        var check = false
                                        for (j in categoryLists.indices) {
                                            if (categoryLists[j].IMDB === item.vote_average!!.toInt()) {
                                                categoryLists.get(j).movieList!!.add(item)
                                                check = true
                                                break
                                            }
                                        }
                                        if (check == false) {
                                            temporaryList.clear()
                                            temporaryList.add(item)
                                            var categoryModel = CategoryModel()
                                            categoryModel.categoryID = AllMovies.get(i).id
                                            categoryModel.IMDB =
                                                AllMovies.get(i).vote_average!!.toInt()
                                            categoryModel.categoryTitle =
                                                "IMDB" + AllMovies.get(i).vote_average!!.toInt()
                                            categoryModel.movieList = temporaryList
                                            categoryLists.add(categoryModel)
                                            temporaryList.clear()
                                        }
                                    } else {
                                        temporaryList.clear()
                                        temporaryList.add(item)
                                        var categoryModel = CategoryModel()
                                        categoryModel.categoryID = item.id
                                        categoryModel.IMDB = item.vote_average!!.toInt()
                                        categoryModel.categoryTitle =
                                            "IMDB" + item.vote_average!!.toInt()
                                        categoryModel.movieList = temporaryList
                                        categoryLists.add(categoryModel)
                                        temporaryList.clear()
                                    }
                                }
                                sortList(categoryLists)
                                categoryLists.remove(categoryLists[categoryLists.size - 1])
                                Collections.reverse(categoryLists)
                                if (categoryLists != null) {
                                    if (categoryLists.size > 0) {
                                        adapter = MovieVerticalAdapter(this, categoryLists)
                                    }
                                }

                                verticalRecyclerView.adapter = adapter
                            }
                        }

                        Status.ERROR -> {

                        }

                        Status.LOADING -> {

                        }
                    }
                }
            })
        }


    }

    private fun sortList(categoryLists: List<CategoryModel>) {
        Collections.sort(categoryLists, object : Comparator<CategoryModel?> {
            override fun compare(p0: CategoryModel?, p1: CategoryModel?): Int {
                val num1: Int? = p0!!.IMDB
                val num2: Int? = p1!!.IMDB
                return num1!!.compareTo(num2!!)
            }
        })
    }

}