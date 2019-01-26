package com.fhomovc.sportsapp.presenters

import com.fhomovc.sportsapp.data.APIManager
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
                onError()
            }
        })
    }

    fun onResponse(response: Response<StoriesResponseWrapper>?) {
        mainView.hideProgress()
        if (response?.body() != null) {
            val list: List<Story> = response.body()!!.items
            mainView.setData(list)
        } else {
            //TODO log error
        }

    }

    fun onError() {
        mainView.hideProgress()
        mainView.showError()
    }

    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setData(stories: List<Story>)
        fun showError()
    }

}