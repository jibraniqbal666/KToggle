package com.ktoggle.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class JsonToggleRaw(@SerializedName("key") val key: String, @SerializedName("value") val value: JsonObject)