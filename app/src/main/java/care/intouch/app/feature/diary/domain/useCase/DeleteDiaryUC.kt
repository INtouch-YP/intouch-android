package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.diary.data.EmotionsRepository
import javax.inject.Inject

interface DeleteDiaryUC {
    suspend operator fun invoke(id: Int): Resource<Boolean, ErrorEntity>
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : DeleteDiaryUC {
        override suspend fun invoke(id: Int): Resource<Boolean, ErrorEntity> {
           return emotionsRepository.deleteDiary(id)
        }
    }
}