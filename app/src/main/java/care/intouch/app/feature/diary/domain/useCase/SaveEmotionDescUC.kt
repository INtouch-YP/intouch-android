package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import javax.inject.Inject

interface SaveEmotionDescUC {
    suspend operator fun invoke(list: MutableList<EmotionDescriptionTask>)
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : SaveEmotionDescUC {
        override suspend fun invoke(list: MutableList<EmotionDescriptionTask>) {
            emotionsRepository.saveEmotionDesc(list)
        }
    }
}