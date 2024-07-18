package care.intouch.app.feature.diary.data.api

import care.intouch.app.feature.diary.data.modals.DiaryDTO
import care.intouch.app.feature.diary.data.modals.Request
import care.intouch.app.feature.diary.data.modals.ResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DiaryApiService {
    @POST("/api/v1/diary-notes/")
    suspend fun saveDiary(@Body request: Request)

    @GET("/api/v1/diary-notes/")
    suspend fun getDiaries(): ResponseDTO<DiaryDTO>

    @PATCH("/api/v1/diary-notes/{id}/visible/")
    suspend fun switchVisible(@Path("id") id: Int): Response<Int>

    @DELETE("/api/v1/diary-notes/{id}/")
    suspend fun deleteDiary(@Path("id") id: Int): Response<Int>
}