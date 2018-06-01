package com.example.bs217.androidhub.repository_details

import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar

import com.example.bs217.androidhub.R
import com.example.bs217.androidhub.main.Item
import com.example.bs217.androidhub.util.BaseActivity
import com.example.bs217.androidhub.util.Consts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repository_details2.*
import kotlinx.android.synthetic.main.fragment_repository_details.view.*
import kotlinx.android.synthetic.main.toolbar_repository_details.*

class RepositoryDetailsActivity : BaseActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    lateinit var repositoryDetail : Item

    override fun isHomeButtonEnabled(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.activity_repository_details2
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun initializer() {

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        repositoryDetail = intent.getSerializableExtra(Consts.REPOSITORY_DETAILS) as Item
        repositoryNameTextView.text = repositoryDetail.name
        createdTextView.text = repositoryDetail.getCreatedAtDate()
        watcherCountTextView.text = repositoryDetail.watchersCount.toString()
        starCountTextView.text = repositoryDetail.star.toString()
        forkCountTextView.text = repositoryDetail.forksCount.toString()
        sizeTextView.text = repositoryDetail.getSize()
        Picasso.get().load(repositoryDetail.owner.imageOfOwner).into(imageOfOwner);

//        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        container.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                Unit
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Unit
            }

            override fun onPageSelected(position: Int) {
                var count = supportFragmentManager.fragments.get(0).childFragmentManager.backStackEntryCount
                while(count!=0) {
                    supportFragmentManager.fragments.get(0).childFragmentManager.popBackStack()
                    count--
                }
            }

        })
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_repository_details, menu)
        return true
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            var bundle: Bundle = Bundle()
            bundle.putString(Consts.OWNERS_ID, repositoryDetail.owner.userName)
            bundle.putString(Consts.REPOSITORY_NAME, repositoryDetail.name)

            return when(position){
                1 -> {
                    var readmeFragment: ReadmeFragment = ReadmeFragment()
                    readmeFragment.arguments = bundle
                    readmeFragment
                }
                else -> {
                    var contentsFragment : ContentsFragment = ContentsFragment()
                    contentsFragment.arguments = bundle
                    contentsFragment
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }

    override fun onBackPressed() {
        val count: Int = supportFragmentManager.fragments.get(0).childFragmentManager.backStackEntryCount
        if(count!=0){
            supportFragmentManager.fragments.get(0).childFragmentManager.popBackStack()
        }
        else
            super.onBackPressed()
    }
}
