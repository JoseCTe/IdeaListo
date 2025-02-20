package com.baeolian.idealisto.data.model

import com.baeolian.idealisto.domain.model.EmissionsModel
import com.baeolian.idealisto.domain.model.EnergyCertificationModel
import com.baeolian.idealisto.domain.model.EnergyConsumptionModel
import com.baeolian.idealisto.domain.model.MoreCharacteristicsModel
import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.model.UbicationModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PropertyDetailsEntity(
    @Json(name = "adid") val adid: Int,
    @Json(name = "price") val price: Double?,
    @Json(name = "priceInfo") val priceInfo: PriceEntity?,
    @Json(name = "operation") val operation: String?,
    @Json(name = "propertyType") val propertyType: String?,
    @Json(name = "extendedPropertyType") val extendedPropertyType: String?,
    @Json(name = "homeType") val homeType: String?,
    @Json(name = "state") val state: String?,
    @Json(name = "multimedia") val multimedia: MultimediaEntity?,
    @Json(name = "propertyComment") val propertyComment: String?,
    @Json(name = "ubication") val ubication: UbicationEntity?,
    @Json(name = "country") val country: String?,
    @Json(name = "moreCharacteristics") val moreCharacteristics: MoreCharacteristicsEntity?,
    @Json(name = "energyCertification") val energyCertification: EnergyCertificationEntity?,
) {
    fun toDomain(): PropertyDetailsModel = PropertyDetailsModel(
        adid = adid,
        price = price,
        priceInfo = priceInfo?.toDomain(),
        operation = operation,
        propertyType = propertyType,
        extendedPropertyType = extendedPropertyType,
        homeType = homeType,
        state = state,
        multimedia = multimedia?.toDomain(),
        propertyComment = propertyComment,
        ubication = ubication?.toDomain(),
        country = country,
        moreCharacteristics = moreCharacteristics?.toDomain(),
        energyCertification = energyCertification?.toDomain()
    )
}

@JsonClass(generateAdapter = true)
data class UbicationEntity(
    @Json(name = "latitude") val latitude: Double?,
    @Json(name = "longitude") val longitude: Double?,
) {
    fun toDomain(): UbicationModel = UbicationModel(
        latitude = latitude,
        longitude = longitude
    )
}

@JsonClass(generateAdapter = true)
data class MoreCharacteristicsEntity(
    @Json(name = "communityCosts") val communityCosts: Double?,
    @Json(name = "roomNumber") val roomNumber: Int?,
    @Json(name = "bathNumber") val bathNumber: Int?,
    @Json(name = "exterior") val exterior: Boolean?,
    @Json(name = "housingFurnitures") val housingFurnitures: String?,
    @Json(name = "agencyIsABank") val agencyIsABank: Boolean?,
    @Json(name = "energyCertificationType") val energyCertificationType: String?,
    @Json(name = "flatLocation") val flatLocation: String?,
    @Json(name = "modificationDate") val modificationDate: Long?,
    @Json(name = "constructedArea") val constructedArea: Int?,
    @Json(name = "lift") val lift: Boolean?,
    @Json(name = "boxroom") val boxroom: Boolean?,
    @Json(name = "isDuplex") val isDuplex: Boolean?,
    @Json(name = "floor") val floor: String?,
    @Json(name = "status") val status: String?,
) {
    fun toDomain(): MoreCharacteristicsModel = MoreCharacteristicsModel(
        communityCosts = communityCosts,
        roomNumber = roomNumber,
        bathNumber = bathNumber,
        exterior = exterior,
        housingFurnitures = housingFurnitures,
        agencyIsABank = agencyIsABank,
        energyCertificationType = energyCertificationType,
        flatLocation = flatLocation,
        modificationDate = modificationDate,
        constructedArea = constructedArea,
        lift = lift,
        boxroom = boxroom,
        isDuplex = isDuplex,
        floor = floor,
        status = status
    )
}

@JsonClass(generateAdapter = true)
data class EnergyCertificationEntity(
    @Json(name = "title") val title: String?,
    @Json(name = "energyConsumption") val energyConsumption: EnergyConsumptionEntity?,
    @Json(name = "emissions") val emissions: EmissionsEntity?,
) {
    fun toDomain(): EnergyCertificationModel = EnergyCertificationModel(
        title = title,
        energyConsumption = energyConsumption?.toDomain(),
        emissions = emissions?.toDomain()
    )
}

@JsonClass(generateAdapter = true)
data class EnergyConsumptionEntity(
    @Json(name = "type") val type: String?,
) {
    fun toDomain(): EnergyConsumptionModel = EnergyConsumptionModel(
        type = type
    )
}

@JsonClass(generateAdapter = true)
data class EmissionsEntity(
    @Json(name = "type") val type: String?,
) {
    fun toDomain(): EmissionsModel = EmissionsModel(
        type = type
    )
}
