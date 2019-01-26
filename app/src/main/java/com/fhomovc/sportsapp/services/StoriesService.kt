package com.fhomovc.sportsapp.services

import com.fhomovc.sportsapp.models.StoriesResponseWrapper
import retrofit2.Call
import retrofit2.http.GET

interface StoriesService {

    @GET("data.json")
    fun requestStories(): Call<StoriesResponseWrapper>

}