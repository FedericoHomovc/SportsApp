package com.fhomovc.sportsapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fhomovc.sportsapp.R
import com.fhomovc.sportsapp.models.Story
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_story.*

class StoryAdapter(private val stories: MutableList<Story>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_story, viewGroup, false).let {
                ViewHolder(it)
            }
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(stories[position])
    }

    fun addStories(stories: List<Story>) {
        this.stories.addAll(stories)
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindData(story: Story) {
            with(story) {
                story_adapter_title.text = title
                story_adapter_time.text = lastUpdatedText
                Picasso.get().load(image.medium).into(story_adapter_image)
            }
        }
    }
}