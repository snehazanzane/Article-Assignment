package com.assignment.assignmentinnofiedsolutionpvtltd.network

class ApiUrl {

    companion object {
        //https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/blogs?page=1&limit=10
        //https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/users?page=1&limit=10
        const val base_url: String = "https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"
        const val get_user_data_api = "users"
        const val get_articles_data_api = "blogs"
    }
}