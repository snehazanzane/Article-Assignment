package com.assignment.articleassignment.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.assignment.articleassignment.R
import com.assignment.articleassignment.data.models.UserModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.view_user_list_item.view.*

class UserDetailsActivity : AppCompatActivity() {
    var objUser: UserModel = UserModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        init()

        setData()
    }

    private fun init() {
        //actionbar
        val actionbar = supportActionBar
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title= getString(R.string.title_user_details)

        objUser = intent.getParcelableExtra("obj")
    }

    private fun setData() {
        textUsername_UserDetailsActivity.setText(objUser.name + " " + objUser.lastname)
        textCity_UserDetailsActivity.setText(objUser.city)
        textDesignation_UserDetailsActivity.setText(objUser.designation)
        textContent_UserDetailsActivity.setText(objUser.about)
        Picasso.get().load(objUser?.avatar)
            .placeholder(R.drawable.ic_user_default)
            .into(imageUser_UserDetailsActivity)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}