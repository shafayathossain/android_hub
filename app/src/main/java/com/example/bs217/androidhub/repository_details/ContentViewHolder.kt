package com.example.bs217.androidhub.repository_details

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.content_card.view.*

class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var contentImageView : ImageView
    lateinit var contentTextView : TextView

    init {
        this.contentImageView = view.contentImageView
        this.contentTextView = view.contentName
    }
}