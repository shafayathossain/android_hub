package com.example.bs217.androidhub.network

import com.example.bs217.androidhub.main.Search
import com.example.bs217.androidhub.repository_details.Contents
import com.example.bs217.androidhub.repository_details.File
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkCall : MyApiServices {

    override fun file(ownersId: String, repositoryName: String, fileName: String, callback: ResponseCallback<File>) {
        var retrofitApiInterface : RetrofitApiInterface = RetrofitApiClient.getClient().
                create(RetrofitApiInterface::class.java)
        var call : Call<File> = retrofitApiInterface.file(ownersId, repositoryName, fileName)
        call.enqueue(object: Callback<File>{

            override fun onResponse(call: Call<File>?, response: Response<File>?) {
                if(response?.body() != null){
                    callback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<File>?, t: Throwable?) {

            }

        })
    }

    override fun contents(ownersId: String, repositoryName: String, path: String, callback: ResponseCallback<MutableList<Contents>>) {
        var retrofitApiInterface : RetrofitApiInterface = RetrofitApiClient.getClient().
                create(RetrofitApiInterface::class.java)
        var call : Call<MutableList<Contents>> = retrofitApiInterface.contents(ownersId, repositoryName, path)
        call.enqueue(object: Callback<MutableList<Contents>>{

            override fun onResponse(call: Call<MutableList<Contents>>?, response: Response<MutableList<Contents>>?) {
                if(response?.body() != null){
                    callback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MutableList<Contents>>?, t: Throwable?) {

            }

        })
    }

    override fun search(topic: String, page:Int, callback: ResponseCallback<Search>) {
        var retrofitApiInterface : RetrofitApiInterface = RetrofitApiClient.getClient().
                create(RetrofitApiInterface::class.java)

        var call : Call<Search> = retrofitApiInterface.
                search(topic, page)
        call.enqueue(object : Callback<Search>{
            override fun onFailure(call: Call<Search>?, t: Throwable?) {
                val error : String = t.toString()
            }

            override fun onResponse(call: Call<Search>?, response: Response<Search>?) {
                if (response != null) {
                    callback.onSuccess(response.body()!!)
                }else{

                }
            }
        })
    }


}
