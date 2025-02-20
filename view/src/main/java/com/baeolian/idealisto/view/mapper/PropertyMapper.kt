package com.baeolian.idealisto.view.mapper

import com.baeolian.idealisto.domain.model.PropertyModel
import com.baeolian.idealisto.view.data.PropertyViewData
import com.baeolian.idealisto.view.utils.toCountryFormat
import com.baeolian.idealisto.view.utils.toEuroFormat

object PropertyMapper {

    fun map(model: PropertyModel): PropertyViewData {
        return PropertyViewData(
            propertyCode = model.propertyCode,
            images = model.multimedia?.images?.mapNotNull { it.url },
            title = "${model.district} • ${model.neighborhood}",
            price = model.priceInfo?.price?.let { "${it.amount?.toInt()?.toEuroFormat()} ${it.currencySuffix}" },
            size = model.size?.toInt().toString(),
            rooms = model.rooms.toString(),
            municipality = model.municipality,
            country = model.country?.toCountryFormat(),
        )
    }
}
