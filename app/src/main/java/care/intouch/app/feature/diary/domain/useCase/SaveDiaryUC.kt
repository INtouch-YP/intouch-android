package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import care.intouch.app.feature.diary.domain.modal.Diary
import javax.inject.Inject

interface SaveDiaryUC {
    suspend operator fun invoke(diary: Diary)
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : SaveDiaryUC {
        override suspend fun invoke(diary: Diary) {
            emotionsRepository.saveDiary(diary)
        }
    }
}