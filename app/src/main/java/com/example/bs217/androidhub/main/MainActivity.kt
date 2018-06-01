package com.example.bs217.androidhub.main

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AbsListView
import butterknife.BindView
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.network.MyApiServices
import com.example.bs217.androidhub.network.NetworkCall
import com.example.bs217.androidhub.network.ResponseCallback
import com.example.bs217.androidhub.util.BaseActivity
import com.example.bs217.androidhub.util.MyUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun isHomeButtonEnabled(): Boolean {
        return false
    }

    var page : Int = 1
    lateinit var repositoriesAdapter: RepositoriesAdapter
    lateinit  var myApiServices : MyApiServices

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initializer(){

        var isScrolling : Boolean = false
        var currentItems : Int
        var totalItems : Int
        var scrollOutItems : Int

        if(MyUtil.isNetworkAvailable(applicationContext)) {
            myApiServices = NetworkCall()
            myApiServices.search(
                    topic = "topic:android",
                    page = page,
                    callback = object : ResponseCallback<Search> {
                        override fun onSuccess(data: Search) {
                            numberOfRepositories.text = data?.totalCount.toString() + " repositories found"

                            repositoriesRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                            repositoriesRecyclerView.itemAnimator = DefaultItemAnimator()
                            repositoriesAdapter = RepositoriesAdapter(data?.items!!)
                            repositoriesRecyclerView.adapter = repositoriesAdapter
                            repositoriesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                                    super.onScrollStateChanged(recyclerView, newState)
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        isScrolling = true
                                    }
                                }

                                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                                    super.onScrolled(recyclerView, dx, dy)

                                    currentItems = recyclerView?.layoutManager?.childCount ?: 0
                                    totalItems = recyclerView?.layoutManager?.itemCount ?: 0
                                    scrollOutItems = (repositoriesRecyclerView.layoutManager as LinearLayoutManager)
                                            .findFirstVisibleItemPosition()

                                    if (currentItems + scrollOutItems == totalItems && isScrolling) {

                                        isScrolling = false

                                        if (page != 100) {
                                            page++
                                            fetchData()
                                        }

                                    }
                                }
                            })
                        }

                        override fun onError(th: Throwable) {


                        }

                    }
            )
        }
    }

    private fun fetchData() {
        myApiServices.search(
                topic="topic:android",
                page = page,
                callback=object:ResponseCallback<Search> {
                    override fun onSuccess(data: Search) {
                        if(data != null) {
                            repositoriesAdapter.addToList(data.items!!)
                            repositoriesAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onError(th: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
    }
}
