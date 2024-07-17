package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.diary.data.EmotionsRepository
import javax.inject.Inject

interface ClearAllInformationUC {
    suspend operator fun invoke()
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : ClearAllInformationUC {
        override suspend fun invoke() {
            emotionsRepository.clearAllInformation()
        }
    }
}