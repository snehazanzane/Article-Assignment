package com.assignment.articleassignment.data.models

import com.google.gson.annotations.SerializedName

class ArticleModel {

    @SerializedName("id")
    var id: String = ""

    @SerializedName("createdAt")
    var createdAt: String = ""

    @SerializedName("content")
    var content: String = ""

    @SerializedName("comments")
    var comments: Long = 0

    @SerializedName("likes")
    var likes: Long = 0

    @SerializedName("media")
    var media: ArrayList<MediaModel> = ArrayList()

    @SerializedName("user")
    var user: ArrayList<UserModel> = ArrayList()

}