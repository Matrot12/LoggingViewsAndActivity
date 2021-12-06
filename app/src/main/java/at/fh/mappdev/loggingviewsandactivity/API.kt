package at.fh.mappdev.loggingviewsandactivity

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

class API {
    object LessonApi {
        const val accessToken = "43aa6187-8209-4978-a0e6-e23fb7c4824f"
        val retrofit: Retrofit
        val retrofitService: LessonApiService
        init {
            val moshi = Moshi.Builder().build()
            retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl("https://lessons.bloder.xyz")
                .build()
            retrofitService = retrofit.create(LessonApiService::class.java)
        }
    }
    interface LessonApiService {
        @GET("/lessons")
        @Headers("X-API-KEY: ${LessonApi.accessToken}")
        fun lessons(): Call<List<Lesson>>

        @POST("/lessons/{id}/rate")
        @Headers("X-API-KEY: ${LessonApi.accessToken}")
        fun rateLesson(@Path("id") lessonId: String, @Body rating: LessonRating): Call<Unit>

        @GET("/lessons/{id}/")
        @Headers("X-API-KEY: ${LessonApi.accessToken}")
        fun lessonById(@Path("id") lessonId: String): Call<Lesson>
    }
}