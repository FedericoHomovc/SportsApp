package com.fhomovc.sportsapp.data

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class StatsRecorder {

    companion object {

        fun createStat(event: String, data: String) {
            val call = APIManager.createStat(event, data)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>?) {
                    Log.v("Sports App", "Stat created")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Sports App", "Error creating stat")
                }
            })
        }
    }
}