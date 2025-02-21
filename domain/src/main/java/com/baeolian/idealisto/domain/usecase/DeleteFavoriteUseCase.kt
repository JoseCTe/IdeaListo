package com.baeolian.idealisto.domain.usecase

import com.baeolian.idealisto.domain.repository.UserRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(propertyCode: String) {
        return userRepository.deleteFavorite(propertyCode)
    }
}
