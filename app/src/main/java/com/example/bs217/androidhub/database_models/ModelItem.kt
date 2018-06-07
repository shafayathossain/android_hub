package com.example.bs217.androidhub.database_models

import com.orm.SugarRecord

class ModelItem : SugarRecord(){

    var itemId : Int = 0
    lateinit var description : String
    lateinit var forksUrl : String
    lateinit var branchesUrl : String
    lateinit var commitsUrl : String
    lateinit var commentsUrl : String
    lateinit var issuesUrl : String
    lateinit var createdAt : String
    lateinit var updatedAt : String
    lateinit var name : String
    var size : Int = 0
    var forksCount : Int = 0
    var openIssuesCount : Int = 0
    var watchersCount : Int = 0
    var score : Double = 0.0
    var star: Int = 0
    
}