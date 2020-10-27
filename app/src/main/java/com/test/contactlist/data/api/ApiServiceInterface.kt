package com.test.contactlist.data.api

import com.test.contactlist.data.models.Contact
import com.test.contactlist.util.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiServiceInterface {

//
//    @Headers("Content-Type:application/json", "AccessKey:A4BC8D32-E0DD-48CD-8A1B-7E0BE88EDA99")
//    @POST("/api/Patient/Login")
//    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>
//
//    @Headers("Content-Type:application/json", "AccessKey:A4BC8D32-E0DD-48CD-8A1B-7E0BE88EDA99")
//    @POST("/api/Patient/RegisterNewPatient")
//    fun RegisterNewPatient(@Body registerRequest: RegisterRequest): Observable<String>
//
//    @Headers("AccessKey:A4BC8D32-E0DD-48CD-8A1B-7E0BE88EDA99")
//    @GET("/api/Recipe/GetRepeatableRecipeSpecificPatient/{Id}")
//    fun GetRepeatableRecipeSpecificPatient(@Path("Id") Id : String) : Observable<List<GetRepeatableRecipeSpecificPatientResponse>>
//
//    @Headers("AccessKey:A4BC8D32-E0DD-48CD-8A1B-7E0BE88EDA99")
//    @GET("/api/Recipe/GetSpecificRecipeDetail/{Id}")
//    fun GetSpecificRecipeDetail(@Path("Id") Id : String) : Observable<List<GetSpecificRecipeDetailResponse>>
//
//    @Headers("AccessKey:A4BC8D32-E0DD-48CD-8A1B-7E0BE88EDA99")
//    @GET("/api/Patient/GetSpecificPatientCompletedAppointment/{Id}")
//    fun GetSpecificPatientCompletedAppointment(@Path("Id") Id : String) : Observable<List<GetSpecificPatientCompletedAppointmentResponse>>


    @GET("contacts")
    fun getContact() : Observable<Contact>


    companion object Factory {

        val retrofit : Retrofit by lazy {
            retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(120, TimeUnit.SECONDS)
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .addInterceptor { chain ->
                            val newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", Constants.TOKEN)
                                .build()
                            chain.proceed(newRequest)
                        }
                        .build())
                .build()
        }
        fun create(): ApiServiceInterface {

            return retrofit.create(ApiServiceInterface::class.java)
        }

    }

}
