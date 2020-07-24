package com.assignment.articleassignment.utils

import android.content.Context
import android.net.ConnectivityManager

class Util {

    companion object Factory {
        fun create(): Util = Util()

        fun convertValueInK(number: Long): String {
            var result: String = ""

            if (Math.abs(number / 1000000) > 1) {
                result = (number / 1000000).toString() + "m"
            } else if (Math.abs(number / 1000) > 1) {
                result = (number / 1000).toString() + "k";
            } else {
                result = number.toString();
            }

            return result
        }
    }


}