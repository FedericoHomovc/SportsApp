package com.fhomovc.sportsapp.models

import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


class ResponseWrapperGsonAdapter : JsonDeserializer<StoriesResponseWrapper> {

    override fun deserialize(json: JsonElement, typeOfT: Type, ctx: JsonDeserializationContext): StoriesResponseWrapper {
        val items = json.asJsonObject.get("data").asJsonObject.get("items").toString()
        val founderListType = object : TypeToken<ArrayList<Story>>() {}.type
        val founderList = Gson().fromJson<ArrayList<Story>>(items, founderListType)
        return StoriesResponseWrapper(founderList)
    }
}
