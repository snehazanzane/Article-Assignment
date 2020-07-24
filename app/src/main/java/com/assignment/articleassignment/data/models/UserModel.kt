package com.assignment.articleassignment.data.models

import com.google.gson.annotations.SerializedName

class UserModel {

    @SerializedName("id")
    var id: String = ""

    @SerializedName("createdAt")
    var createdAt: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("avatar")
    var avatar: String = ""

    @SerializedName("lastname")
    var lastname: String = ""

    @SerializedName("city")
    var city: String = ""

    @SerializedName("designation")
    var designation: String = ""

    @SerializedName("about")
    var about: String = ""

}