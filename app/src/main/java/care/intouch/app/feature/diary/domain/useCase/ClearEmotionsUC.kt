package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import javax.inject.Inject

interface ClearEmotionsUC {
    suspend operator fun invoke()
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : ClearEmotionsUC {
        override suspend fun invoke() {
            emotionsRepository.clearEmotions()
        }

    }
}