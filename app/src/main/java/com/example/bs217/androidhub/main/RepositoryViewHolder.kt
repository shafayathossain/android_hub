package com.example.bs217.androidhub.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.util.BaseViewHolder
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.repository_card.view.*

open class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var repositoryNameTextView : TextView
    var starCountTextView : TextView
    var forkCountTextView : TextView
    var lastPushTextView : TextView
    var sizeTextView : TextView
    var imageOfowner : CircleImageView

    init {
        this.repositoryNameTextView = view.repositoryNameTextView
        starCountTextView = view.starCountTextView
        forkCountTextView = view.forkCountTextView
        lastPushTextView = view.lastPushTextView
        sizeTextView = view.sizeTextView
        imageOfowner = view.imageOfOwner
    }

}
