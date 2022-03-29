package com.ktoggle.models

import com.google.gson.JsonObject

data class KJsonToggle(val kJson: JsonObject) : KToggle<JsonObject>() {
    override fun getValue(): JsonObject = kJson

    override fun setValue(): JsonObject {
        TODO("Not yet implemented")
    }
}