package com.example.roomdatabaseexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.adapter.UserAdapter
import com.example.roomdatabaseexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetails : AppCompatActivity() {
    var userViewModel: UserViewModel? = null
    var adapter: UserAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel!!.clearUserData(this)
        for (i in 0 until 4000) {
            userViewModel!!.insertData(this, "User$i", "Data$i", "Address$i")
        }
        adapter = UserAdapter()
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = adapter
        userViewModel!!.getUserData(this).observe(this, Observer {
            adapter!!.setData(it)
        })
    }
}