package care.intouch.app.feature.diary.data

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.diary.domain.modal.Diary
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import kotlinx.coroutines.flow.Flow

interface EmotionsRepository {
    suspend fun saveEmotion(emotionDesc: EmotionDesc)
    suspend fun saveEmotionDesc(list: MutableList<EmotionDescriptionTask>)
    suspend fun getEmotion(): String
    suspend fun getEmotionDesc(): List<EmotionDescriptionEnum>
    suspend fun clearEmotions()
    suspend fun saveAnswers(details: String, analysis: String, type: String, sensation: String)
    suspend fun clearAllInformation()
    suspend fun getSavedAnswers(): List<String>

    suspend fun saveDiary(diary: Diary)
    suspend fun getDiaries(): Resource<List<Diary>, ErrorEntity>
    suspend fun deleteDiary(id: Int): Resource<Boolean, ErrorEntity>
    suspend fun switchVisible(id: Int): Resource<Boolean, ErrorEntity>
}