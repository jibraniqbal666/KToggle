package com.ktoggle.models

data class KStringToggle(val kString: String) : KToggle<String>() {
    override fun getValue(): String = kString

    override fun setValue(): String {
        TODO("Not yet implemented")
    }
}