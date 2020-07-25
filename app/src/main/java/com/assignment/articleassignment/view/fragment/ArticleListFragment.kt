package com.assignment.articleassignment.view.fragment

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.articleassignment.R
import com.assignment.articleassignment.data.adapter.ArticleAdapter
import com.assignment.articleassignment.data.models.ArticleModel
import com.assignment.articleassignment.utils.NetworkConnectivity
import com.assignment.assignmentinnofiedsolutionpvtltd.network.APIinterface
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_article_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleListFragment : Fragment() {
    var arrayListArticleData: ArrayList<ArticleModel?> = ArrayList<ArticleModel?>()

    var page_number: Int = 1
    var per_page: Int = 10

    var isMoreArticleAvailabel: Boolean = true

    lateinit var mArticleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(
            R.layout.fragment_article_list,
            container, false
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //** Set the adapter of the RecyclerView
        setAdapter()

        //** Set the data for our ArrayList
        getItemsData()

        //** Set the Layout Manager of the RecyclerView
        setRVLayoutManager()

        //** Set the colors of the Pull To Refresh View
        userSwipeToRefreshFunctionality()
    }

    /**
     * Get User List Data from Rest API(Network available) / Local DB(Network NOT available)
     */
    private fun getItemsData() {
        if (activity?.let { NetworkConnectivity.isConnected(it) }!!) {
            //Get data from Rest API Call -->>
            val progressDialog = ProgressDialog(activity)
            progressDialog.setTitle("Please wait...")
            progressDialog.show()
            APIinterface().getArticles(page_number, per_page)
                .enqueue(object : Callback<ArrayList<ArticleModel>> {
                    override fun onFailure(call: Call<ArrayList<ArticleModel>>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(
                            activity,
                            getString(R.string.msg_unable_to_fatch_data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<ArrayList<ArticleModel>>,
                        response: Response<ArrayList<ArticleModel>>
                    ) {
                        response.body()?.let {
                            progressDialog.dismiss()
                            if (it?.size <= 0) {
                                isMoreArticleAvailabel = false
                            }
                            //total_records = it.total
                            arrayListArticleData.addAll(it)
                            mArticleAdapter.notifyDataSetChanged()
                        }
                    }
                })
        } else {
            NetworkConnectivity.showNetworkAlert(activity!!)
        }
    }

    /**
     * Binding user data to Adapter
     */
    fun setAdapter() {
        mArticleAdapter = activity?.let { ArticleAdapter(it, arrayListArticleData) }!!
        recyclerview_ArticleFragment.layoutManager = LinearLayoutManager(this.context)
        recyclerview_ArticleFragment.adapter = mArticleAdapter
    }

    fun setRVLayoutManager() {
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true
        recyclerview_ArticleFragment.layoutManager = mLayoutManager
        recyclerview_ArticleFragment.setHasFixedSize(true)
    }

    private fun userSwipeToRefreshFunctionality() {
        activity?.let {
            ContextCompat.getColor(
                it,
                R.color.colorAccent
            )
        }?.let {
            itemsswipetorefresh_ArticleFragment.setProgressBackgroundColorSchemeColor(
                it
            )
        }
        itemsswipetorefresh_ArticleFragment.setColorSchemeColors(Color.WHITE)

        /**
         * Up Swipe to refresh functionality
         */
        itemsswipetorefresh_ArticleFragment.setOnRefreshListener {


            if (isMoreArticleAvailabel) {
                page_number = +page_number + 1
                getItemsData()
                recyclerview_ArticleFragment.adapter = mArticleAdapter
                itemsswipetorefresh_ArticleFragment.isRefreshing = false
                recyclerview_ArticleFragment.smoothScrollToPosition(1)
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.msg_no_more_data_available),
                    Toast.LENGTH_SHORT
                )
                    .show()
                itemsswipetorefresh_ArticleFragment.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        this.clearFindViewByIdCache();
    }
}