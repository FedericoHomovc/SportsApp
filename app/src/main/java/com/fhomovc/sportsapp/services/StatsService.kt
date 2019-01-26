package com.fhomovc.sportsapp.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StatsService {

    @GET("stats")
    fun createStat(@Query("event") event: String, @Query("data") data: String): Call<ResponseBody>
}
