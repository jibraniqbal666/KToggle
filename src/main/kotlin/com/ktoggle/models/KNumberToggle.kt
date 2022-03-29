package com.ktoggle.models

data class KNumberToggle(val kNumber: Number) : KToggle<Number>() {
    override fun getValue(): Number = kNumber

    override fun setValue(): Number {
        TODO("Not yet implemented")
    }
}