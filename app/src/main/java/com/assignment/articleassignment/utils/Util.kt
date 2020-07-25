package com.assignment.articleassignment.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Util {

    companion object Factory {
        fun create(): Util = Util()

        fun convertValueInK(number: Long): String {
            var result: String = ""

            if (Math.abs(number / 1000000) > 1) {
                result = (number / 1000000).toString() + "M"
            } else if (Math.abs(number / 1000) > 1) {
                result = (number / 1000).toString() + "K";
            } else {
                result = number.toString();
            }

            return result
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getTimePeriodBetweenDate(strDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val date: Date = inputFormat.parse(strDate)

            val currentDateTime = LocalDateTime.now()

            println("Date : " + date + " - " + currentDateTime)
            return printDifference(date, Calendar.getInstance().time)


        }

        fun printDifference(startDate: Date, endDate: Date): String {
            //milliseconds
            var different = endDate.time - startDate.time
            /*println("startDate : $startDate")
            println("endDate : $endDate")
            println("different : $different")*/
            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24
            val elapsedDays = different / daysInMilli
            different = different % daysInMilli
            val elapsedHours = different / hoursInMilli
            different = different % hoursInMilli
            val elapsedMinutes = different / minutesInMilli
            different = different % minutesInMilli
            val elapsedSeconds = different / secondsInMilli
            System.out.printf(
                "Date : %d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
            )


            if (elapsedDays > 0) {
                return elapsedDays.toString() + " days"
            } else if (elapsedHours > 0) {
                return elapsedHours.toString() + " hours"
            } else if (elapsedMinutes > 0) {
                return elapsedMinutes.toString() + " minutes"
            } else if (elapsedSeconds > 0) {
                return elapsedSeconds.toString() + " seconds"
            } else {
                return "0 seconds"
            }
        }
    }


}