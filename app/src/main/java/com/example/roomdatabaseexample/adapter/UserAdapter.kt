package com.example.roomdatabaseexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

/*
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val userModel = ArrayList<UserModel>()

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userModel: UserModel?) {
            if (userModel != null) {
                itemView.materialTextView3.setText(userModel.Username)
                itemView.textView3.setText(userModel.UserSurename)
                itemView.txtAddress.setText(userModel.UserAddres)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userModel.get(position))
    }

    fun setData(user: List<UserModel>) {
        val diffCallback = UserDiffcallBack(userModel, user)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        userModel.clear()
        userModel.addAll(user)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return userModel.size
    }

    inner class UserDiffcallBack(
        private var oldList: List<UserModel>,
        private var newList: List<UserModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].UserId === newList.get(newItemPosition).UserId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val (_, value, name) = oldList[oldItemPosition]
            val (_, value1, name1) = newList[newItemPosition]

            return name == name1 && value == value1
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}*/

class UserAdapter : PagedListAdapter<UserModel,UserAdapter.UserViewHolder>(UserDiffcallBack()) {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userModel: UserModel?) {
            if (userModel != null) {
                itemView.materialTextView3.setText(userModel.Username)
                itemView.textView3.setText(userModel.UserSurename)
                itemView.txtAddress.setText(userModel.UserAddres)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

     class UserDiffcallBack : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.UserId == newItem.UserId
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

    }
}
