package care.intouch.app.feature.diary.domain.useCase

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.diary.data.EmotionsRepository
import care.intouch.app.feature.diary.domain.modal.Diary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetDiariesUC {
    suspend operator fun invoke(): Resource<List<Diary>, ErrorEntity>
    class Base @Inject constructor(
        private val emotionsRepository: EmotionsRepository,
    ) : GetDiariesUC {
        override suspend fun invoke(): Resource<List<Diary>, ErrorEntity> {
           return emotionsRepository.getDiaries()
        }
    }
}