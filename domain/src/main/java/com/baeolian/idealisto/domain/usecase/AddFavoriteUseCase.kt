package com.baeolian.idealisto.domain.usecase

import com.baeolian.idealisto.domain.repository.UserRepository
import java.util.Date
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(propertyCode: String, date: Date) {
        return userRepository.addFavorite(propertyCode, date)
    }
}
