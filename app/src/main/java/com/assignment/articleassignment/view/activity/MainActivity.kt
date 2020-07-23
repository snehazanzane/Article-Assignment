package com.assignment.articleassignment.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.assignment.articleassignment.R
import com.assignment.articleassignment.data.adapter.MainViewPagerAdapter
import com.assignment.articleassignment.view.fragment.ArticleListFragment
import com.assignment.articleassignment.view.fragment.UserListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_article_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ArticleListFragment(), getString(R.string.tab_article))
        adapter.addFragment(UserListFragment(), getString(R.string.tab_users))
        viewPager_MainActivity.adapter = adapter
        tabs_MainActivity.setupWithViewPager(viewPager_MainActivity)

    }
}