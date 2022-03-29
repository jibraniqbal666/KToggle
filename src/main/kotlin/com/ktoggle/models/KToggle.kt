package com.ktoggle.models

abstract class KToggle<T> {
    abstract fun getValue(): T
    abstract fun setValue(): T
}