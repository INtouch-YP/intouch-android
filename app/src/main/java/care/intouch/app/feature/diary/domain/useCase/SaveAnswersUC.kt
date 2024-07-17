package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import javax.inject.Inject

interface SaveAnswersUC {
    suspend operator fun invoke(details: String, analysis: String, type: String, sensation: String)
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : SaveAnswersUC {
        override suspend fun invoke(
            details: String,
            analysis: String,
            type: String,
            sensation: String,
        ) {
            emotionsRepository.saveAnswers(details, analysis, type, sensation)
        }

    }
}