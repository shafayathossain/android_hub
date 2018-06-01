package com.example.bs217.androidhub.util

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toolbar

public abstract class BaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setActionBar(getToolbar())
        if(getToolbar() != null) {
            setActionBar(getToolbar())
            actionBar.setHomeButtonEnabled(isHomeButtonEnabled());
            actionBar.setDisplayHomeAsUpEnabled(isHomeButtonEnabled());
            getToolbar()?.setNavigationOnClickListener { v: View -> onBackPressed() }
        }
        initializer()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }



    open fun getToolbar(): Toolbar? {
        return null
    }

    abstract fun isHomeButtonEnabled(): Boolean

    open fun initializer() {
    }

    abstract fun getLayout():Int

}