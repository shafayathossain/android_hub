package com.example.bs217.androidhub.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.method.TextKeyListener.clear
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View.Z



abstract class BaseRecyclerViewAdapter<T, Z: RecyclerView.ViewHolder> : RecyclerView.Adapter<Z>() {

    protected lateinit var mContext: Context
    protected lateinit var mItems: MutableList<T>

    fun BaseRecyclerViewAdapter(items: MutableList<T>, context: Context){
        var items = items
        if (items == null) {
            items = ArrayList()
        }
        mItems = items
        mContext = context
    }

    fun add(item: T?, position: Int) {
        if (item == null) {
            return
        }
        mItems.add(position, item)
        notifyItemInserted(position)
    }

    fun add(items: List<T>?, position: Int) {
        if (items == null || items.isEmpty()) {
            return
        }
        mItems.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    fun add(item: T) {
        val position = mItems.size
        mItems.add(position, item)
        notifyItemInserted(position)
    }

    fun add(items: List<T>) {
        if (items.isEmpty()) {
            return
        }
        val position = if (mItems.isEmpty()) 0 else mItems.size
        mItems.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    fun getItem(position: Int): T {
        return mItems[position]
    }

    fun getItems(): List<T> {
        return mItems
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /**
     * Requires equals() and hashcode() to be implemented in T class.
     */
    fun remove(item: T) {
        val position = mItems.indexOf(item)
        if (position == -1) {
            return
        }
        mItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(position: Int, itemCount: Int) {
        for (i in position until itemCount) {
            mItems.removeAt(i)
        }
        notifyItemRangeRemoved(position, itemCount)
    }

    fun replaceList(items: MutableList<T>) {
        mItems = items
        notifyDataSetChanged()
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }
}