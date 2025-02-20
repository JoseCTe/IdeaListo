package com.baeolian.idealisto.view.data

data class PropertyViewData(
    val propertyCode: String,
    val isFavorite: Boolean = false,
    val images: List<String>?,
    val title: String?,
    val price: String?,
    val size: String?,
    val rooms: String?,
    val municipality: String?,
)
