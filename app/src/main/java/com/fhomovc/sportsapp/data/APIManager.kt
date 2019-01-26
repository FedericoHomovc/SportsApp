package com.fhomovc.sportsapp.data

import com.fhomovc.sportsapp.adapters.ResponseWrapperGsonAdapter
import com.fhomovc.sportsapp.models.StoriesResponseWrapper
import com.fhomovc.sportsapp.services.StatsService
import com.fhomovc.sportsapp.services.StoriesService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {

    private const val BASE_URL: String = "https://bbc.github.io/sport-app-dev-tech-challenge/"

    private var storiesService: StoriesService
    private var statsService: StatsService

    init {

        val gson = GsonBuilder()
            .registerTypeAdapter(
                StoriesResponseWrapper::class.java,
                ResponseWrapperGsonAdapter()
            )
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        storiesService = retrofit.create<StoriesService>(StoriesService::class.java)
        statsService = retrofit.create<StatsService>(StatsService::class.java)
    }

    fun loadStories() = storiesService.requestStories()
    fun createStat(event: String, data: String) = statsService.createStat(event, data)

}