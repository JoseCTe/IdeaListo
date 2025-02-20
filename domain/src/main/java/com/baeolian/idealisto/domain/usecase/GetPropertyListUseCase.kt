package com.baeolian.idealisto.domain.usecase

import com.baeolian.idealisto.domain.model.PropertyModel
import com.baeolian.idealisto.domain.repository.UserRepository
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<List<PropertyModel>> {
        return userRepository.getPropertyList()
    }
}
