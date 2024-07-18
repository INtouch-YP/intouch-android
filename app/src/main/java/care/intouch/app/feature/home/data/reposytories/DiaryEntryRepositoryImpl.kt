package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.home.data.api.DiaryNotesApi
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.DiaryEntryRepository
import care.intouch.app.feature.home.domain.models.DiaryEntry
import javax.inject.Inject

class DiaryEntryRepositoryImpl @Inject constructor(
    private val diaryNoteApi: DiaryNotesApi,
    private val mapper: HomeMapper
) : DiaryEntryRepository {
    override suspend fun getDiaryEntries(userId: Int): Result<List<DiaryEntry>> {
        try {
            val request = mapper.mapDiaryNoteRequest(userId = userId)
            val response = diaryNoteApi.getDiaryNotes(request = request)

            return Result.success(mapper.mapDiaryEntries(response))

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun deleteDiaryEntry(diaryNoteId: Int): Result<String> {
        return try {
            Result.success(DELETE_RESPONSE)

        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun setDiaryEntryVisible(diaryNoteId: Int): Result<String> {
        try {
            val response = diaryNoteApi.setDiaryNoteVisible(id = diaryNoteId)
            return Result.success(response.message)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    companion object {
        const val DELETE_RESPONSE = "Diary deleted"
    }
}