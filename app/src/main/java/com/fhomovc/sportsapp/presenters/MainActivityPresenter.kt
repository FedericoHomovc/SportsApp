package com.fhomovc.sportsapp.presenters

import com.fhomovc.sportsapp.data.APIManager
import com.fhomovc.sportsapp.data.StatsRecorder
import com.fhomovc.sportsapp.models.StoriesResponseWrapper
import com.fhomovc.sportsapp.models.Story
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityPresenter(private var mainView: MainView) {

    fun loadStories() {
        mainView.showProgress()
        val call = APIManager.loadStories()
        call.enqueue(object : Callback<StoriesResponseWrapper> {
            override fun onResponse(
                call: Call<StoriesResponseWrapper>,
                response: retrofit2.Response<StoriesResponseWrapper>?
            ) {
                onResponse(response)
            }

            override fun onFailure(call: Call<StoriesResponseWrapper>, t: Throwable) {
                onError(t.message.orEmpty())
            }
        })
    }

    fun onResponse(response: Response<StoriesResponseWrapper>?) {
        mainView.hideProgress()
        if (response?.body() != null) {
            val list: List<Story> = response.body()!!.items
            mainView.setData(list)
            StatsRecorder.createStat("display", System.currentTimeMillis().toString())
        } else {
            StatsRecorder.createStat("error", "Empty response body")
        }
    }

    fun onError(message: String) {
        mainView.hideProgress()
        mainView.showError()
        StatsRecorder.createStat("error", message)
    }


    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setData(stories: List<Story>)
        fun showError()
    }

}