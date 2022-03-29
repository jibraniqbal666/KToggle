package com.ktoggle.models

data class KBooleanToggle(val value: Boolean): KToggle<Boolean>() {
    override fun getValue(): Boolean = value

    override fun setValue(): Boolean {
        TODO("Not yet implemented")
    }
}