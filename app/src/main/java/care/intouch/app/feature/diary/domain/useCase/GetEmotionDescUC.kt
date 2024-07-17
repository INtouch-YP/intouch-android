package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionEnum
import javax.inject.Inject

interface GetEmotionDescUC {
    suspend operator fun invoke(): List<EmotionDescriptionEnum>
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : GetEmotionDescUC {
        override suspend fun invoke(): List<EmotionDescriptionEnum> {
            return emotionsRepository.getEmotionDesc()
        }
    }
}