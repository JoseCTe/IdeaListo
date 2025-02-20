package com.baeolian.idealisto.domain.usecase

import com.baeolian.idealisto.domain.model.PropertyDetailsModel
import com.baeolian.idealisto.domain.repository.UserRepository
import javax.inject.Inject

class GetPropertyDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<PropertyDetailsModel> {
        return userRepository.getPropertyDetails()
    }
}
