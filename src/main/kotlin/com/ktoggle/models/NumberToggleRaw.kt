package com.ktoggle.models

import com.google.gson.annotations.SerializedName

data class NumberToggleRaw(@SerializedName("key") val key: String, @SerializedName("value") val value: Number)