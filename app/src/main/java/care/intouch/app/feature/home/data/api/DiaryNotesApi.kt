package care.intouch.app.feature.home.data.api

import care.intouch.app.feature.home.data.models.DiaryNotesResponse
import care.intouch.app.feature.home.data.models.RegularResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface DiaryNotesApi {
    @GET("/api/v1/diary-notes/")
    suspend fun getDiaryNotes(@QueryMap request: Map<String, String>): DiaryNotesResponse

    @DELETE("/api/v1/diary-notes/{id}/")
    suspend fun deleteDiaryNote(@Path("id") id: Int): Response<RegularResponse?>

    @PATCH("/api/v1/diary-notes/{id}/visible/")
    suspend fun setDiaryNoteVisible(@Path("id") id: Int): RegularResponse
}