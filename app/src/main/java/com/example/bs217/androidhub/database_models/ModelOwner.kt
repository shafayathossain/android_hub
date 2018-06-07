package com.example.bs217.androidhub.database_models

import com.orm.SugarRecord


class ModelOwner : SugarRecord(){

    lateinit var userName : String
    var userId : Int = 0
    lateinit var imageOfOwner : String
    lateinit var profileUrl : String
    lateinit var followers : String
    lateinit var followings : String
    lateinit var reposOfOwner : String
}