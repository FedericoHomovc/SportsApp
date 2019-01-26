package com.fhomovc.sportsapp.adapters

import com.fhomovc.sportsapp.models.StoriesResponseWrapper
import com.fhomovc.sportsapp.models.Story
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ResponseWrapperGsonAdapter : JsonDeserializer<StoriesResponseWrapper> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        ctx: JsonDeserializationContext
    ): StoriesResponseWrapper {
        val items = json.asJsonObject.get("data").asJsonObject.get("items").toString()
        val founderListType = object : TypeToken<ArrayList<Story>>() {}.type
        val founderList = Gson().fromJson<ArrayList<Story>>(items, founderListType)
        return StoriesResponseWrapper(founderList)
    }
}
