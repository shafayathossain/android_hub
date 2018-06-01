package com.example.bs217.androidhub.repository_details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.network.MyApiServices
import com.example.bs217.androidhub.network.NetworkCall
import com.example.bs217.androidhub.network.ResponseCallback
import com.example.bs217.androidhub.util.Consts
import kotlinx.android.synthetic.main.fragment_readme.*

class ReadmeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_readme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var myApiServices : MyApiServices = NetworkCall()
        var bundle : Bundle? = arguments
        var ownersId: String? = bundle?.getString(Consts.OWNERS_ID, null)
        var repositoryName: String? = bundle?.getString(Consts.REPOSITORY_NAME, null)

        myApiServices.file(ownersId!!, repositoryName!!, "README.md", callback = object:ResponseCallback<File>{
            override fun onSuccess(data: File) {

                markdownView.setMarkDownText(data.getContentString())
            }

            override fun onError(th: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun onResume() {
        super.onResume()

    }

}