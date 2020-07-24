package com.assignment.assignmentinnofiedsolutionpvtltd.network

import com.assignment.articleassignment.data.models.ArticleModel
import com.assignment.articleassignment.data.models.UserModel
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface APIinterface {

    @GET(ApiUrl.get_user_data_api)
    fun getUsers(
        @Query("page") page: Int,
        @Query("limit") per_page: Int
    ): Call<ArrayList<UserModel>>

    @GET(ApiUrl.get_articles_data_api)
    fun getArticles(
        @Query("page") page: Int,
        @Query("limit") per_page: Int
    ): Call<ArrayList<ArticleModel>>

    companion object {
        operator fun invoke(): APIinterface {

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiUrl.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIinterface::class.java)

        }
    }
}