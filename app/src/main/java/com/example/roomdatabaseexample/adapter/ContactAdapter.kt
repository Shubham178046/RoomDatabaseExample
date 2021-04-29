package com.example.roomdatabaseexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.ContactModel
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contactModel = ArrayList<ContactModel>()

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contactModel: ContactModel?) {
            if (contactModel != null) {
                itemView.txtContactID.setText(contactModel.contactId)
                itemView.txtContactName.setText(contactModel.contactName)
                itemView.txtContactNumber.setText(contactModel.contactNumber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactModel.get(position))
    }

    fun setData(user: List<ContactModel>) {
        val diffCallback = ContactDiffcallBack(contactModel, user)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        contactModel.clear()
        contactModel.addAll(user)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return contactModel.size
    }

    inner class ContactDiffcallBack(
        private var oldList: List<ContactModel>,
        private var newList: List<ContactModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].contactId === newList.get(newItemPosition).contactId
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
}