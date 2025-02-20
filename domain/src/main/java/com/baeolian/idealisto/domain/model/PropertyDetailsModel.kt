package com.baeolian.idealisto.domain.model

data class PropertyDetailsModel(
    val adid: Int,
    val price: Double?,
    val priceInfo: PriceModel?,
    val operation: String?,
    val propertyType: String?,
    val extendedPropertyType: String?,
    val homeType: String?,
    val state: String?,
    val multimedia: MultimediaModel?,
    val propertyComment: String?,
    val ubication: UbicationModel?,
    val country: String?,
    val moreCharacteristics: MoreCharacteristicsModel?,
    val energyCertification: EnergyCertificationModel?,
)

data class UbicationModel(
    val latitude: Double?,
    val longitude: Double?,
)

data class MoreCharacteristicsModel(
    val communityCosts: Double?,
    val roomNumber: Int?,
    val bathNumber: Int?,
    val exterior: Boolean?,
    val housingFurnitures: String?,
    val agencyIsABank: Boolean?,
    val energyCertificationType: String?,
    val flatLocation: String?,
    val modificationDate: Long?,
    val constructedArea: Int?,
    val lift: Boolean?,
    val boxroom: Boolean?,
    val isDuplex: Boolean?,
    val floor: String?,
    val status: String?,
)

data class EnergyCertificationModel(
    val title: String?,
    val energyConsumption: EnergyConsumptionModel?,
    val emissions: EmissionsModel?,
)

data class EnergyConsumptionModel(
    val type: String?,
)

data class EmissionsModel(
    val type: String?,
)
