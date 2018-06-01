package com.example.bs217.androidhub.main

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.repository_details.RepositoryDetailsActivity
import com.example.bs217.androidhub.util.Consts
import com.example.bs217.androidhub.util.MyUtil
import com.squareup.picasso.Picasso

class RepositoriesAdapter(repositoryList: MutableList<Item>) : RecyclerView.Adapter<RepositoryViewHolder>() {

    lateinit var repositoryList : MutableList<Item>

    init {
        this.repositoryList = repositoryList
    }

    fun addToList(repositoryList: MutableList<Item>){
        this.repositoryList.addAll(repositoryList)
    }

    override fun getItemCount(): Int {
        return repositoryList.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.repository_card, parent, false)

        return RepositoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.repositoryNameTextView.text = repositoryList.get(position).name
        holder.starCountTextView.text = repositoryList.get(position).star.toString()
        holder.forkCountTextView.text = repositoryList.get(position).forksCount.toString()
        val(a, b) = repositoryList.get(position).getUpdateAt()
        holder.lastPushTextView.text = when(a){
            1 -> b.toString() + "hours ago"
            else -> b.toString() + "days ago"
        }
        holder.sizeTextView.text = repositoryList.get(position).getSize()
        Picasso.get().load(repositoryList.get(position).owner.imageOfOwner).into(holder.imageOfowner);

        holder.itemView.setOnClickListener { v: View? ->

            if(MyUtil.isNetworkAvailable(holder.itemView.context)) {
                var intent: Intent = Intent(holder.itemView.context, RepositoryDetailsActivity::class.java)
                intent.putExtra(Consts.REPOSITORY_DETAILS, repositoryList.get(position))
                holder.itemView.context.startActivity(intent)
            }
            else{
                Toast.makeText(holder.itemView.context, "Connect to internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

}