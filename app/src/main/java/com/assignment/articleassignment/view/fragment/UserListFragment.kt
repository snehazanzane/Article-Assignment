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
import com.assignment.articleassignment.data.adapter.UserAdapter
import com.assignment.articleassignment.data.models.UserModel
import com.assignment.articleassignment.utils.NetworkConnectivity
import com.assignment.assignmentinnofiedsolutionpvtltd.network.APIinterface
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.android.synthetic.main.fragment_user_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment : Fragment() {
    var arrayListUserData: ArrayList<UserModel?> = ArrayList<UserModel?>()

    var page_number: Int = 1
    var per_page: Int = 10

    var isMoreUserAvailable: Boolean = true

    lateinit var mUserAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(
            R.layout.fragment_user_list,
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

            APIinterface().getUsers(page_number, per_page)
                .enqueue(object : Callback<ArrayList<UserModel>> {
                    override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(
                            activity,
                            getString(R.string.msg_unable_to_fatch_data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<ArrayList<UserModel>>,
                        response: Response<ArrayList<UserModel>>
                    ) {
                        response.body()?.let {
                            progressDialog.dismiss()
                            if (it?.size <= 0) {
                                isMoreUserAvailable = false
                            }
                            arrayListUserData.addAll(it)
                            mUserAdapter.notifyDataSetChanged()
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
        mUserAdapter = activity?.let { UserAdapter(it, arrayListUserData) }!!
        recyclerview_UserListFragment.layoutManager = LinearLayoutManager(this.context)
        recyclerview_UserListFragment.adapter = mUserAdapter
    }

    fun setRVLayoutManager() {
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true
        recyclerview_UserListFragment.layoutManager = mLayoutManager
        recyclerview_UserListFragment.setHasFixedSize(true)
    }

    private fun userSwipeToRefreshFunctionality() {
        activity?.let {
            ContextCompat.getColor(
                it,
                R.color.colorAccent
            )
        }?.let {
            itemsswipetorefresh_UserListFragment.setProgressBackgroundColorSchemeColor(
                it
            )
        }
        itemsswipetorefresh_UserListFragment.setColorSchemeColors(Color.WHITE)

        /**
         * Up Swipe to refresh functionality
         */
        itemsswipetorefresh_UserListFragment.setOnRefreshListener {
            if (isMoreUserAvailable) {
                page_number = +page_number + 1
                getItemsData()
                recyclerview_UserListFragment.adapter = mUserAdapter
                itemsswipetorefresh_UserListFragment.isRefreshing = false
                recyclerview_UserListFragment.smoothScrollToPosition(1)
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.msg_no_more_data_available),
                    Toast.LENGTH_SHORT
                )
                    .show()
                itemsswipetorefresh_UserListFragment.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        this.clearFindViewByIdCache();
    }
}