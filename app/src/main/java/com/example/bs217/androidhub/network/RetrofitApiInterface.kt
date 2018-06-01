package com.example.bs217.androidhub.network

import com.example.bs217.androidhub.main.Search
import com.example.bs217.androidhub.repository_details.Contents
import com.example.bs217.androidhub.repository_details.File
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface RetrofitApiInterface{

    @GET("search/repositories")
    fun search(@Query("q", encoded = true)topic:String,
               @Query("page")page:Int,
               @Query("per_page")perPage:Int=10) : Call<Search>

    @GET("repos/{owners_id}/{repository_name}/contents/{path}")
    fun contents(@Path("owners_id", encoded=true)ownersId: String,
                 @Path("repository_name", encoded=true)repositoryName: String,
                 @Path("path", encoded = true)path : String) : Call<MutableList<Contents>>

    @GET("repos/{owners_id}/{repository_name}/contents/{file_name}")
    fun file(@Path("owners_id", encoded=true)ownersId: String,
             @Path("repository_name", encoded=true)repositoryName: String,
             @Path("file_name", encoded = true)fileName : String) : Call<File>

}