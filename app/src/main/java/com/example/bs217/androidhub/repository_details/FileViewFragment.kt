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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_file_view.*

class FileViewFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_file_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var myApiServices : MyApiServices = NetworkCall()
        var bundle : Bundle? = arguments
        var ownersId: String? = bundle?.getString(Consts.OWNERS_ID, null)
        var repositoryName: String? = bundle?.getString(Consts.REPOSITORY_NAME, null)
        var path: String? = bundle?.getString(Consts.PATH, "")

        myApiServices.file(ownersId!!, repositoryName!!, path!!, callback = object: ResponseCallback<File> {
            override fun onSuccess(data: File) {
                var updatePath = path.toLowerCase()

                if(updatePath.endsWith(".png") or
                        updatePath.endsWith(".jpg") or
                        updatePath.endsWith(".jpeg") or
                        updatePath.endsWith(".svg") or
                        updatePath.endsWith(".bmp")){

                    Picasso.get().load(data.downloadUrl).into(imageView);
                    imageView.visibility = View.VISIBLE
                }
                else if(updatePath.endsWith(".md")){
                    markdownView.setMarkDownText(data.getContentString())
                    markdownView.visibility = View.VISIBLE
                }
                else{
                    codeView.setCode(data.getContentString())
                    codeView.visibility = View.VISIBLE
                }
            }

            override fun onError(th: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}