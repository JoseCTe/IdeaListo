package com.baeolian.idealisto.domain.usecase

import com.baeolian.idealisto.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Map<String, Date>> {
        return userRepository.getFavorites()
    }
}
