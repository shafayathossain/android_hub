package com.example.bs217.androidhub.repository_details

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.network.MyApiServices
import com.example.bs217.androidhub.network.NetworkCall
import com.example.bs217.androidhub.network.ResponseCallback
import com.example.bs217.androidhub.util.Consts
import kotlinx.android.synthetic.main.activity_repository_details2.*
import kotlinx.android.synthetic.main.activity_repository_details2.view.*
import kotlinx.android.synthetic.main.fragment_files.*

class FilesFragment : Fragment(){

    lateinit var container: ViewGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.container = container!!
        return inflater!!.inflate(R.layout.fragment_files, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var myApiServices : MyApiServices = NetworkCall()
        var bundle : Bundle? = arguments
        var ownersId: String? = bundle?.getString(Consts.OWNERS_ID, null)
        var repositoryName: String? = bundle?.getString(Consts.REPOSITORY_NAME, null)
        var path: String? = bundle?.getString(Consts.PATH, "")

        myApiServices.contents(ownersId!!, repositoryName!!, path!!, callback = object:ResponseCallback<MutableList<Contents>>{
            override fun onSuccess(data: MutableList<Contents>) {
                showFiles(data, ownersId, repositoryName)
            }

            override fun onError(th: Throwable) {

            }

        })
    }

    private fun showFiles(data: MutableList<Contents>, ownersId: String, repositoryName: String) {
        contentsRecyclerView.layoutManager = LinearLayoutManager(context)
        var contentsAdapter : ContentsAdapter = ContentsAdapter(data, ownersId, repositoryName)
        contentsRecyclerView.adapter = contentsAdapter

    }

}