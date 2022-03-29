package com.ktoggle.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.bson.codecs.pojo.annotations.BsonId

data class JsonToggleRaw(
    @BsonId
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: JsonObject
)