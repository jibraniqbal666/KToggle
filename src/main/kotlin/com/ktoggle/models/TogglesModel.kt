package com.ktoggle.models

data class TogglesModel(
    val numberToggles: List<NumberToggleRaw>,
    val booleanToggles: List<BooleanToggleRaw>,
    val stringToggles: List<StringToggleRaw>
)