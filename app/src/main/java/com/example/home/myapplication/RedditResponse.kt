package com.example.home.myapplication

/**
 * Created by home on 2/10/2018.
 */
class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(val children: List<RedditChildrenResponse>)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)