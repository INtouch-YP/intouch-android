package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import javax.inject.Inject

interface GetEmotionUC {
    suspend operator fun invoke(): String
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : GetEmotionUC {
        override suspend fun invoke(): String {
            return emotionsRepository.getEmotion()
        }
    }
}