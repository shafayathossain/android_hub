package com.example.bs217.androidhub.repository_details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.util.Consts

class ContentsAdapter(data : MutableList<Contents>,
                      ownersId: String,
                      repositoryName: String) : RecyclerView.Adapter<ContentViewHolder>() {

    lateinit var contents :  MutableList<Contents>
    lateinit var ownersId : String
    lateinit var repositoryName : String

    init {
        this.contents = data
        this.ownersId = ownersId
        this.repositoryName = repositoryName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_card, parent, false)
        return ContentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return contents.count()
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        if(contents.get(position).type.equals("file")) {
            holder.contentImageView.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.file))
        }

        holder.contentTextView.text = contents.get(position).name

//        holder.itemView.setOnClickListener { v: View? -> run{
//

//

//
//        }
//        }

        holder.itemView.setOnClickListener { v: View ->
            var bundle: Bundle = Bundle()
            bundle.putString(Consts.OWNERS_ID, ownersId)
            bundle.putString(Consts.REPOSITORY_NAME, repositoryName)
            bundle.putString(Consts.PATH, contents.get(position).path)

            if(contents.get(position).type.equals("dir")) {

                var filesFragment: FilesFragment = FilesFragment()
                filesFragment.arguments = bundle
                (holder.itemView.context as RepositoryDetailsActivity).
                        supportFragmentManager.
                        fragments.
                        get(0).
                        childFragmentManager.
                        beginTransaction().
                        replace(R.id.contentsFrameLayout, filesFragment).
                        addToBackStack(null).
                        commit()
            }
            else{
                var fileViewFragment : FileViewFragment = FileViewFragment()
                fileViewFragment.arguments = bundle

                (holder.itemView.context as RepositoryDetailsActivity).
                        supportFragmentManager.
                        fragments.
                        get(0).
                        childFragmentManager.
                        beginTransaction().
                        replace(R.id.contentsFrameLayout, fileViewFragment).
                        addToBackStack(null).
                        commit()
            }
        }
    }



}