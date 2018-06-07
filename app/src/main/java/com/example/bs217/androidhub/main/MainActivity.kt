package com.example.bs217.androidhub.main

import android.database.sqlite.SQLiteException
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AbsListView
import butterknife.BindView
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.database_models.ModelItem
import com.example.bs217.androidhub.database_models.ModelOwner
import com.example.bs217.androidhub.database_models.ModelSearch
import com.example.bs217.androidhub.network.MyApiServices
import com.example.bs217.androidhub.network.NetworkCall
import com.example.bs217.androidhub.network.ResponseCallback
import com.example.bs217.androidhub.util.BaseActivity
import com.example.bs217.androidhub.util.MyUtil
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun isHomeButtonEnabled(): Boolean {
        return false
    }

    var page : Int = 1
    lateinit var repositoriesAdapter: RepositoriesAdapter
    lateinit  var myApiServices : MyApiServices
    var isScrolling : Boolean = false
    var currentItems : Int = 0
    var totalItems : Int = 0
    var scrollOutItems : Int = 0

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initializer(){

        if(MyUtil.isNetworkAvailable(applicationContext)) {
            myApiServices = NetworkCall()
            myApiServices.search(
                    topic = "topic:android",
                    page = page,
                    callback = object : ResponseCallback<Search> {
                        override fun onSuccess(data: Search) {
                            saveData(data)

                            numberOfRepositories.text = data?.totalCount.toString() + " repositories found"

                            showRecyclerView(data.items)
                        }

                        override fun onError(th: Throwable) {


                        }

                    }
            )
        }
        else{


            var ms : MutableList<ModelSearch> = SugarRecord.listAll(ModelSearch::class.java)
            var mi : MutableList<ModelItem> = SugarRecord.listAll(ModelItem::class.java)
            var mo : MutableList<ModelOwner> = SugarRecord.listAll(ModelOwner::class.java)

            var items : MutableList<Item> = ArrayList()
            var size = mi.size-1
            if (mi.size>mo.size)
                size = mo.size-1

            for(i in 0..size){

                var anOwner : Owner = Owner(
                followers = mo.get(i).followers,
                followings = mo.get(i).followings,
                id = mo.get(i).userId,
                imageOfOwner = mo.get(i).imageOfOwner,
                profileUrl = mo.get(i).profileUrl,
                reposOfOwner = mo.get(i).reposOfOwner,
                userName = mo.get(i).userName
                )

                var anItem : Item = Item(
                        branchesUrl = mi.get(i).branchesUrl,
                        watchersCount = mi.get(i).watchersCount,
                        updatedAt = mi.get(i).updatedAt,
                        star = mi.get(i).star,
                        size = mi.get(i).size,
                        score = mi.get(i).score,
                        owner = anOwner,
                        commentsUrl = mi.get(i).commentsUrl,
                        commitsUrl = mi.get(i).commitsUrl,
                        createdAt = mi.get(i).createdAt,
                        description = mi.get(i).description,
                        forksCount = mi.get(i).forksCount,
                        forksUrl = mi.get(i).forksUrl,
                        id = mi.get(i).itemId,
                        issuesUrl = mi.get(i).issuesUrl,
                        name = mi.get(i).name,
                        openIssuesCount = mi.get(i).openIssuesCount
                        )

                items.add(i, anItem)
            }


            numberOfRepositories.text = ms.get(0).totalCount.toString() + " repositories found"
            showRecyclerView(items);
        }
    }

    private fun showRecyclerView(items: MutableList<Item>) {
        repositoriesRecyclerView.layoutManager = LinearLayoutManager(applicationContext)!!
        repositoriesRecyclerView.itemAnimator = DefaultItemAnimator()
        repositoriesAdapter = RepositoriesAdapter(items)
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
                        if(MyUtil.isNetworkAvailable(applicationContext)) {
                            fetchData()
                        }
                    }

                }
            }
        })
    }

    private fun saveData(data: Search) {
        var modelItems : MutableList<ModelItem> = ArrayList()

        for(i in 0..data.items.size-1){
            lateinit var modelOwner: ModelOwner
//
            try {
                var mo: MutableList<ModelOwner> = SugarRecord.listAll(ModelOwner::class.java)

                var j: Int
                j = 0
                while (j <= mo.size - 1) {

                    if (mo.get(j).userId == data.items.get(i).owner.id)
                        break
                    j++

                }

                if (j == mo.size) {
                    modelOwner = ModelOwner()
                } else {
                    modelOwner = mo.get(j)
                }
            }catch (e: SQLiteException){
                modelOwner = ModelOwner()
            }

            modelOwner.userName = data.items.get(i).owner.userName
            modelOwner.userId = data.items.get(i).owner.id
            modelOwner.imageOfOwner = data.items.get(i).owner.imageOfOwner
            modelOwner.profileUrl = data.items.get(i).owner.profileUrl
            modelOwner.followers = data.items.get(i).owner.followers
            modelOwner.followings = data.items.get(i).owner.followings
            modelOwner.reposOfOwner = data.items.get(i).owner.reposOfOwner

            lateinit var modelItem: ModelItem
            try {
                var mi: MutableList<ModelItem> = SugarRecord.listAll(ModelItem::class.java)

                var k: Int
                k = 0
                while (k <= mi.size - 1) {

                    if (mi.get(k).itemId == data.items.get(i).id)
                        break
                    k++

                }

                if (k == mi.size) {
                    modelItem = ModelItem()
                } else {
                    modelItem = mi.get(k)
                }
            }catch (e : SQLiteException){
                modelItem = ModelItem()
            }
            modelItem.branchesUrl = data.items.get(i).branchesUrl
            modelItem.commentsUrl = data.items.get(i).commentsUrl
            modelItem.commitsUrl = data.items.get(i).commitsUrl
            modelItem.createdAt = data.items.get(i).createdAt
            modelItem.description = data.items.get(i).description
            modelItem.forksCount = data.items.get(i).forksCount
            modelItem.forksUrl = data.items.get(i).forksUrl
            modelItem.itemId = data.items.get(i).id
            modelItem.issuesUrl = data.items.get(i).issuesUrl
            modelItem.openIssuesCount = data.items.get(i).openIssuesCount
            modelItem.score = data.items.get(i).score
            modelItem.size = data.items.get(i).size
            modelItem.star = data.items.get(i).star
            modelItem.updatedAt = data.items.get(i).updatedAt
            modelItem.watchersCount = data.items.get(i).watchersCount
            modelItem.name = data.items.get(i).name

            modelItem.save()
            modelOwner.save()
        }
        var modelSearch : ModelSearch = ModelSearch()
        modelSearch.totalCount = data.totalCount
        println("start")
        modelSearch.save()
        println("end")

    }

    private fun fetchData() {

        myApiServices.search(
                topic="topic:android",
                page = page,
                callback=object:ResponseCallback<Search> {
                    override fun onSuccess(data: Search) {
                        if(data != null) {
                            saveData(data)
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
