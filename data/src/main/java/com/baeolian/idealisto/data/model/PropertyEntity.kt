package com.baeolian.idealisto.data.model

import com.baeolian.idealisto.domain.model.FeaturesModel
import com.baeolian.idealisto.domain.model.ImageModel
import com.baeolian.idealisto.domain.model.MultimediaModel
import com.baeolian.idealisto.domain.model.ParkingSpaceModel
import com.baeolian.idealisto.domain.model.PriceInfoModel
import com.baeolian.idealisto.domain.model.PriceModel
import com.baeolian.idealisto.domain.model.PropertyModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PropertyEntity(
    @Json(name = "propertyCode") val propertyCode: String,
    @Json(name = "thumbnail") val thumbnail: String?,
    @Json(name = "floor") val floor: String?,
    @Json(name = "price") val price: Double?,
    @Json(name = "priceInfo") val priceInfo: PriceInfoEntity?,
    @Json(name = "propertyType") val propertyType: String?,
    @Json(name = "operation") val operation: String?,
    @Json(name = "size") val size: Double?,
    @Json(name = "exterior") val exterior: Boolean?,
    @Json(name = "rooms") val rooms: Int?,
    @Json(name = "bathrooms") val bathrooms: Int?,
    @Json(name = "address") val address: String?,
    @Json(name = "province") val province: String?,
    @Json(name = "municipality") val municipality: String?,
    @Json(name = "district") val district: String?,
    @Json(name = "country") val country: String?,
    @Json(name = "neighborhood") val neighborhood: String?,
    @Json(name = "latitude") val latitude: Double?,
    @Json(name = "longitude") val longitude: Double?,
    @Json(name = "description") val description: String?,
    @Json(name = "multimedia") val multimedia: MultimediaEntity?,
    @Json(name = "parkingSpace") val parkingSpace: ParkingSpaceEntity?,
    @Json(name = "features") val features: FeaturesEntity?,
) {
    fun toDomain(): PropertyModel = PropertyModel(
        propertyCode = propertyCode,
        thumbnail = thumbnail,
        floor = floor,
        price = price,
        priceInfo = priceInfo?.toDomain(),
        propertyType = propertyType,
        operation = operation,
        size = size,
        exterior = exterior,
        rooms = rooms,
        bathrooms = bathrooms,
        address = address,
        province = province,
        municipality = municipality,
        district = district,
        country = country,
        neighborhood = neighborhood,
        latitude = latitude,
        longitude = longitude,
        description = description,
        multimedia = multimedia?.toDomain(),
        parkingSpace = parkingSpace?.toDomain(),
        features = features?.toDomain()
    )
}

@JsonClass(generateAdapter = true)
data class PriceInfoEntity(
    @Json(name = "price") val price: PriceEntity?,
) {
    fun toDomain(): PriceInfoModel = PriceInfoModel(
        price = price?.toDomain()
    )
}

@JsonClass(generateAdapter = true)
data class PriceEntity(
    @Json(name = "amount") val amount: Double?,
    @Json(name = "currencySuffix") val currencySuffix: String?,
) {
    fun toDomain(): PriceModel = PriceModel(
        amount = amount,
        currencySuffix = currencySuffix
    )
}

@JsonClass(generateAdapter = true)
data class MultimediaEntity(
    @Json(name = "images") val images: List<ImageEntity>?,
) {
    fun toDomain(): MultimediaModel = MultimediaModel(
        images = images?.map { it.toDomain() }
    )
}

@JsonClass(generateAdapter = true)
data class ImageEntity(
    @Json(name = "url") val url: String?,
    @Json(name = "tag") val tag: String?,
    @Json(name = "localizedName") val localizedName: String?,
    @Json(name = "multimediaId") val multimediaId: Int?,
) {
    fun toDomain(): ImageModel = ImageModel(
        url = url,
        tag = tag,
        localizedName = localizedName,
        multimediaId = multimediaId
    )
}

@JsonClass(generateAdapter = true)
data class ParkingSpaceEntity(
    @Json(name = "hasParkingSpace") val hasParkingSpace: Boolean?,
    @Json(name = "isParkingSpaceIncludedInPrice") val isParkingSpaceIncludedInPrice: Boolean?,
) {
    fun toDomain(): ParkingSpaceModel = ParkingSpaceModel(
        hasParkingSpace = hasParkingSpace,
        isParkingSpaceIncludedInPrice = isParkingSpaceIncludedInPrice
    )
}

@JsonClass(generateAdapter = true)
data class FeaturesEntity(
    @Json(name = "hasAirConditioning") val hasAirConditioning: Boolean?,
    @Json(name = "hasBoxRoom") val hasBoxRoom: Boolean?,
    @Json(name = "hasSwimmingPool") val hasSwimmingPool: Boolean?,
    @Json(name = "hasTerrace") val hasTerrace: Boolean?,
    @Json(name = "hasGarden") val hasGarden: Boolean?,
) {
    fun toDomain(): FeaturesModel = FeaturesModel(
        hasAirConditioning = hasAirConditioning,
        hasBoxRoom = hasBoxRoom,
        hasSwimmingPool = hasSwimmingPool,
        hasTerrace = hasTerrace,
        hasGarden = hasGarden
    )
}
