package com.baeolian.idealisto.domain.model

data class PropertyModel(
    val propertyCode: String,
    val thumbnail: String?,
    val floor: String?,
    val price: Double?,
    val priceInfo: PriceInfoModel?,
    val propertyType: String?,
    val operation: String?,
    val size: Double?,
    val exterior: Boolean?,
    val rooms: Int?,
    val bathrooms: Int?,
    val address: String?,
    val province: String?,
    val municipality: String?,
    val district: String?,
    val country: String?,
    val neighborhood: String?,
    val latitude: Double?,
    val longitude: Double?,
    val description: String?,
    val multimedia: MultimediaModel?,
    val parkingSpace: ParkingSpaceModel?,
    val features: FeaturesModel?
)

data class PriceInfoModel(
    val price: PriceModel?
)

data class PriceModel(
    val amount: Double?,
    val currencySuffix: String?
)

data class MultimediaModel(
    val images: List<ImageModel>?
)

data class ImageModel(
    val url: String?,
    val tag: String?,
    val localizedName: String?,
    val multimediaId: Int?
)

data class ParkingSpaceModel(
    val hasParkingSpace: Boolean?,
    val isParkingSpaceIncludedInPrice: Boolean?
)

data class FeaturesModel(
    val hasAirConditioning: Boolean?,
    val hasBoxRoom: Boolean?,
    val hasSwimmingPool: Boolean?,
    val hasTerrace: Boolean?,
    val hasGarden: Boolean?
)
