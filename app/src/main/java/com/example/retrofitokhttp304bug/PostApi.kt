package com.example.retrofitokhttp304bug

import com.example.retrofitokhttp304bug.data.PostDO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url


private const val ACCEPT = "application/vnd.nuglif.rubicon.card+json;modules=\"ad,audio,author,button,collapsible,gallery,photo,post,publicationDates,text,userAction,video,web\""

interface PostApi {

    @GET
    fun fetchPostDO(@Url url: String,
                    @Header("Accept") accept: String = ACCEPT): Call<PostDO>
}
