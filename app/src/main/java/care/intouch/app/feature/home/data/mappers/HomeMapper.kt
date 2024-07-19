package care.intouch.app.feature.home.data.mappers

import care.intouch.app.feature.home.data.models.AssignmentsResponse
import care.intouch.app.feature.home.data.models.ClarifyingEmotionDto
import care.intouch.app.feature.home.data.models.DiaryNotesResponse
import care.intouch.app.feature.home.data.models.StatusDto
import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Mood
import care.intouch.app.feature.home.domain.models.Status
import care.intouch.app.feature.home.domain.models.Task
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class HomeMapper @Inject constructor() {
    fun mapAssignments(response: AssignmentsResponse): List<Task> {
        val mappedResponse = response.assignments.map { assignment ->
            Task(
                id = assignment.id,
                description = assignment.text,
                isSharedWithDoctor = changeToBoolean(assignment.share),
                status = mapStatus(assignment.status)
            )
        }
        val mappedResponseList = mutableListOf<Task>()
        val onlyToDo = mappedResponse
            .filter { it.status == Status.TO_DO }
            .take(MAX_COUNT_OF_TASK_BY_STATUS)
        val onlyInProgress = mappedResponse
            .filter { it.status == Status.IN_PROGRESS }
            .take(MAX_COUNT_OF_TASK_BY_STATUS)

        mappedResponseList.addAll(onlyToDo)
        mappedResponseList.addAll(onlyInProgress)

        return mappedResponseList.toList()
    }

    fun mapDiaryEntries(response: DiaryNotesResponse): List<DiaryEntry> {
        val diaryEntries = response.diariesNotes.take(MAX_COUNT_OF_DIARY_NOTES).map { diaryNote ->
            DiaryEntry(
                id = diaryNote.id,
                date = formatDate(diaryNote.addDate),
                note = diaryNote.eventDetails,
                moodList = mapEmotions(
                    emotions = diaryNote.clarifyingEmotion,
                    primaryEmotion = diaryNote.primaryEmotion
                ),
                isSharedWithDoctor = diaryNote.visible
            )
        }
        return diaryEntries
    }

    fun mapAssignmentsRequest(
        userId: Int, limit: Int? = null,
        page: Int? = null
    ): HashMap<String, String> {
        val request = hashMapOf(USER to "$userId")
        if (limit != null) {
            request[LIMIT] = "$limit"
        }
        if (page != null) {
            request[PAGE] = "$page"
        }
        return request
    }

    fun mapDiaryNoteRequest(
        userId: Int,
        limit: Int? = null,
        page: Int? = null
    ): HashMap<String, String> {
        val request = hashMapOf(USER to "$userId")
        if (limit != null) {
            request[LIMIT] = "$limit"
        }
        if (page != null) {
            request[PAGE] = "$page"
        }
        return request
    }

    private fun mapEmotions(
        emotions: List<ClarifyingEmotionDto>,
        primaryEmotion: String
    ): List<Mood> {
        val moodList = emotions.map {
            Mood.valueOf(it.name)
        }
        val fullMoodList: MutableList<Mood> = mutableListOf()
        if (primaryEmotion.isNotBlank()) {

            fullMoodList.addAll(moodList)
            fullMoodList.add(index = 0, element = Mood.valueOf(primaryEmotion))
        }

        return fullMoodList

    }

    private fun mapStatus(status: String): Status {
        val statusResult = when (status) {
            StatusDto.TO_DO.status -> Status.TO_DO
            StatusDto.IN_PROGRESS.status -> Status.IN_PROGRESS
            StatusDto.DONE.status -> Status.DONE
            else -> Status.UNKNOWN
        }

        return statusResult
    }

    private fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault()
        )
        val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        return inputFormat.parse(date)
            ?.let {
                outputFormat.format(it)
                    .lowercase(Locale.ENGLISH)
                    .filterNot { char -> char == '.' }
            } ?: ""
    }

    private fun changeToBoolean(number: Int): Boolean {
        return number == 1
    }

    companion object {
        const val MAX_COUNT_OF_TASK_BY_STATUS = 3
        const val MAX_COUNT_OF_DIARY_NOTES = 6
        const val LIMIT = "limit"
        const val PAGE = "page"
        const val USER = "user"
    }

}