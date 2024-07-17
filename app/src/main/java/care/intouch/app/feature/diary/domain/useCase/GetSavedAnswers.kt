package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import javax.inject.Inject

interface GetSavedAnswers {
    suspend operator fun invoke(): List<String>
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : GetSavedAnswers {
        override suspend fun invoke(): List<String> {
            return emotionsRepository.getSavedAnswers()
        }

    }
}