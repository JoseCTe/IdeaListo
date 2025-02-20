package com.baeolian.idealisto.view.data

import com.baeolian.idealisto.view.utils.Country
import java.util.Date

data class PropertyViewData(
    val propertyCode: String,
    val dateOfFavorite: Date? = null,
    val images: List<String>?,
    val title: String?,
    val price: String?,
    val size: String?,
    val rooms: String?,
    val municipality: String?,
    val country: Country?,
)
