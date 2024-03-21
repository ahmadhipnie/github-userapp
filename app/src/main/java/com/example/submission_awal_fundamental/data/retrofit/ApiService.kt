package com.example.submission_awal_fundamental.data.retrofit

import com.example.submission_awal_fundamental.data.response.DetailUserResponse
import com.example.submission_awal_fundamental.data.response.ItemsItem
import com.example.submission_awal_fundamental.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("search/users")
    fun getSearchUsers(
        @retrofit2.http.Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String?
    ): Call<DetailUserResponse>


    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
        ) : Call<List<ItemsItem>>


    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
        ) : Call<List<ItemsItem>>


    @GET("users")
    fun getUser(): Call<List<ItemsItem>>


}