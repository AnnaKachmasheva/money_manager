package com.example.moneymanager.model

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("images")
    val images: ArrayList<String>? = ArrayList(),
) : java.io.Serializable
