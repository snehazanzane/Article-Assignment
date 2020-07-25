package com.assignment.articleassignment.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel() : Parcelable {

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

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toString()
        createdAt = parcel.readString().toString()
        name = parcel.readString().toString()
        avatar = parcel.readString().toString()
        lastname = parcel.readString().toString()
        city = parcel.readString().toString()
        designation = parcel.readString().toString()
        about = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(createdAt)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeString(lastname)
        parcel.writeString(city)
        parcel.writeString(designation)
        parcel.writeString(about)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

}