package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.home.domain.DiaryEntryRepository
import javax.inject.Inject

interface DiaryEntriesInteractor {
    suspend fun deleteDiaryEntry(diaryNoteId: Int): Result<String>
    suspend fun shareDiaryEntryWithDoctor(diaryNoteId: Int): Result<String>
    class Base @Inject constructor(private val repository: DiaryEntryRepository) :
        DiaryEntriesInteractor {
        override suspend fun deleteDiaryEntry(diaryNoteId: Int): Result<String> {
            return repository.deleteDiaryEntry(diaryNoteId = diaryNoteId)
        }

        override suspend fun shareDiaryEntryWithDoctor(diaryNoteId: Int): Result<String> {
            return repository.setDiaryEntryVisible(diaryNoteId = diaryNoteId)
        }
    }
}