package com.ktoggle.models

import com.ktoggle.models.KToggle

data class KNumberToggle(val kNumber: Number) : KToggle<Number>() {
    override fun getValue(): Number = kNumber

    override fun setValue(): Number {
        TODO("Not yet implemented")
    }
}