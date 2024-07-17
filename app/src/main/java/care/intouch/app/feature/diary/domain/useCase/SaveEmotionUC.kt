package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDesc
import javax.inject.Inject

interface SaveEmotionUC {
    suspend operator fun invoke(emotionDesc: EmotionDesc)
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : SaveEmotionUC {
        override suspend fun invoke(emotionDesc: EmotionDesc) {
            emotionsRepository.saveEmotion(emotionDesc)
        }
    }
}