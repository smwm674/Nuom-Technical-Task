package com.nuom.technicaltask.data.model

import com.google.gson.annotations.SerializedName

data class SearchJoke(
    @SerializedName("total")
    val total: Float,
    @SerializedName("result")
    val result: ArrayList<Joke>? = ArrayList()
)