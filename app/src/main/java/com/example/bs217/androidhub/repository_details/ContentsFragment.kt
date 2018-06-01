package com.example.bs217.androidhub.repository_details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bs217.androidhub.R

class ContentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var filesFragment : FilesFragment = FilesFragment()
        filesFragment.arguments = arguments

        var fragmentManager : FragmentManager = childFragmentManager
        fragmentManager.beginTransaction().replace(R.id.contentsFrameLayout, filesFragment).commit()
    }



}