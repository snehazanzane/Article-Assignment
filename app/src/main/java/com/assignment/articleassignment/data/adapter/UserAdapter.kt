package com.assignment.articleassignment.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.articleassignment.R
import com.assignment.articleassignment.data.models.UserModel
import com.assignment.articleassignment.view.activity.UserDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_user_list_item.view.*

class UserAdapter(public var context: Context,private var arrayListUserData: ArrayList<UserModel?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_user_list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayListUserData.size
    }

    fun setUserData(arrUser: ArrayList<UserModel?>) {
        arrayListUserData = arrUser
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.itemView.textUsername_UserListItem.text =
            arrayListUserData.get(position)?.name + " " + arrayListUserData.get(position)?.lastname
        itemViewHolder.itemView.textDesignation_UserListItem.text =
            arrayListUserData.get(position)?.designation
        itemViewHolder.itemView.textUserCity_UserListItem.text =
            arrayListUserData.get(position)?.city

        Picasso.get().load(arrayListUserData.get(position)?.avatar)
            .placeholder(R.drawable.ic_user_default)
            .into(itemViewHolder.itemView.imageUser_UserListItem)

        itemViewHolder.itemView.setOnClickListener(View.OnClickListener {
            var intent:Intent = Intent(context,UserDetailsActivity::class.java)
            intent.putExtra("obj",arrayListUserData.get(position))
            context.startActivity(intent)
        })
    }
}