package care.intouch.app.feature.diary.data

import android.content.SharedPreferences
import androidx.core.content.edit
import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.diary.data.api.DiaryApiService
import care.intouch.app.feature.diary.data.modals.Request
import care.intouch.app.feature.diary.data.modals.toDiary
import care.intouch.app.feature.diary.domain.modal.Diary
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EmotionsRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val json: Json,
    private val api: DiaryApiService,
    private val exceptionToErrorMapper: UserExceptionToErrorMapper,
) : EmotionsRepository {
    override suspend fun saveEmotion(emotionDesc: EmotionDesc) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit { putString(EMOTION_KEY, emotionDesc.name) }
        }
    }

    override suspend fun saveEmotionDesc(list: MutableList<EmotionDescriptionTask>) {
        val result: List<String> = list.map {
            it.emotionDescriptionEnum.name
        }
        withContext(Dispatchers.IO) {
            val data = json.encodeToString(result)
            sharedPreferences.edit { putString(DESC_KEY, data) }
        }
    }

    override suspend fun saveAnswers(
        details: String,
        analysis: String,
        type: String,
        sensation: String,
    ) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit { putString(DETAILS, details) }
            sharedPreferences.edit { putString(ANALYSIS, analysis) }
            sharedPreferences.edit { putString(TYPE, type) }
            sharedPreferences.edit { putString(SENSATION, sensation) }
        }
    }

    override suspend fun getSavedAnswers(): List<String> {
        val result: MutableList<String> = mutableListOf()
        withContext(Dispatchers.IO) {
            result.add(sharedPreferences.getString(DETAILS, null).orEmpty())
            result.add(sharedPreferences.getString(ANALYSIS, null).orEmpty())
            result.add(sharedPreferences.getString(TYPE, null).orEmpty())
            result.add(sharedPreferences.getString(SENSATION, null).orEmpty())
        }
        return result
    }

    override suspend fun saveDiary(diary: Diary) {
        api.saveDiary(mapperToRequest(diary))
    }

    private fun mapperToRequest(diary: Diary) = Request(
        clarifyingEmotion = diary.clarifyingEmotion,
        eventDetails = diary.eventDetails,
        primaryEmotion = diary.primaryEmotion,
        visible = diary.visible,
        thoughtsAnalysis = diary.thoughtsAnalysis,
        physicalSensations = diary.physicalSensations,
        emotionType = diary.emotionType
    )

    override suspend fun getDiaries(): Resource<List<Diary>, ErrorEntity> {
        return try {
            (Resource.Success(api.getDiaries().diaryDTOS.map { it.toDiary() }))
        } catch (e: Exception) {
            (Resource.Error(exceptionToErrorMapper.handleException(e)))
        }
    }

    override suspend fun deleteDiary(id: Int): Resource<Boolean, ErrorEntity> {
        return try {
            Resource.Success(api.deleteDiary(id).isSuccessful)
        } catch (e: Exception) {
            Resource.Error(exceptionToErrorMapper.handleException(e))
        }
    }

    override suspend fun switchVisible(id: Int): Resource<Boolean, ErrorEntity> {
        return try {
            Resource.Success(api.switchVisible(id).isSuccessful)
        } catch (e: Exception) {
            Resource.Error(exceptionToErrorMapper.handleException(e))
        }
    }

    override suspend fun clearAllInformation() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().remove(EMOTION_KEY).apply()
            sharedPreferences.edit().remove(DESC_KEY).apply()
            sharedPreferences.edit().remove(DETAILS).apply()
            sharedPreferences.edit().remove(ANALYSIS).apply()
            sharedPreferences.edit().remove(TYPE).apply()
            sharedPreferences.edit().remove(SENSATION).commit()
        }
    }

    override suspend fun getEmotion(): String {
        var result: String = ""
        withContext(Dispatchers.IO) {
            result = sharedPreferences.getString(EMOTION_KEY, null).orEmpty()
        }
        return result
    }

    override suspend fun getEmotionDesc(): List<EmotionDescriptionEnum> {
        var sharedResult = ""
        var result: List<EmotionDescriptionEnum>
        withContext(Dispatchers.IO) {
            sharedResult = sharedPreferences.getString(DESC_KEY, null).orEmpty()
            result = if (sharedResult.isNotEmpty()) {
                json.decodeFromString<List<EmotionDescriptionEnum>>(sharedResult)
            } else {
                listOf()
            }
        }
        return result
    }

    override suspend fun clearEmotions() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().remove(EMOTION_KEY).apply()
            sharedPreferences.edit().remove(DESC_KEY).commit()
        }
    }

    companion object {
        const val EMOTION_KEY = "EMOTION"
        const val DESC_KEY = "DESC_KEY"
        const val DETAILS = "DETAILS"
        const val ANALYSIS = "ANALYSIS"
        const val TYPE = "TYPE"
        const val SENSATION = "SENSATION"
    }
}