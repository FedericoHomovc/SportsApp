package com.fhomovc.sportsapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.fhomovc.sportsapp.R
import com.fhomovc.sportsapp.adapters.StoryAdapter
import com.fhomovc.sportsapp.models.Story
import com.fhomovc.sportsapp.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.MainView {

    private var mPresenter: MainActivityPresenter = MainActivityPresenter(this)
    private var mAdapter: StoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        mPresenter.loadStories()
    }

    private fun setUpRecyclerView() {
        mAdapter = StoryAdapter(ArrayList())
        stories_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        stories_list_recycler_view.adapter = mAdapter
    }

    override fun hideProgress() {
        stories_list_recycler_view.visibility = View.VISIBLE
        stories_list_spinner.visibility = View.GONE
    }

    override fun showProgress() {
        stories_list_spinner.visibility = View.VISIBLE
        stories_list_recycler_view.visibility = View.GONE
    }

    override fun setData(stories: List<Story>) {
        mAdapter?.addStories(stories)
        mAdapter?.notifyDataSetChanged()
    }

    override fun showError() {
        Toast.makeText(this@MainActivity, R.string.network_error_msg, Toast.LENGTH_LONG).show()
    }
}
