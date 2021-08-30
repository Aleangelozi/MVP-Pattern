package com.aleangelozi.mvppattern.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var countryName: String
)
