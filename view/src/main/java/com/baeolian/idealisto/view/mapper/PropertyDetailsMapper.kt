package com.baeolian.idealisto.view.mapper

import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.view.data.PropertyDetailsViewData
import com.baeolian.idealisto.view.utils.toEuroFormat

object PropertyDetailsMapper {

    fun map(model: PropertyDetailsModel): PropertyDetailsViewData {
        return PropertyDetailsViewData(
            images = model.multimedia?.images?.mapNotNull { it.url },
            price = model.priceInfo?.let { "${it.amount?.toInt()?.toEuroFormat()} ${it.currencySuffix}" },
            area = model.moreCharacteristics?.constructedArea?.toString(),
            rooms = model.moreCharacteristics?.roomNumber.toString(),
            floor = model.moreCharacteristics?.floor.toString(),
            baths = model.moreCharacteristics?.bathNumber.toString(),
            energy = model.moreCharacteristics?.energyCertificationType?.uppercase(),
            description = model.propertyComment,
        )
    }
}
