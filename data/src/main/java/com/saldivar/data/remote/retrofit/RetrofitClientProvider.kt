package com.saldivar.data.remote

import com.saldivar.data.remote.common.ServiceUrl.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
val retrofitClient: Retrofit = buildRetrofit(getOkHttpClient(getLoggingInterceptor()))

fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun buildRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun getOkHttpClient(logging: HttpLoggingInterceptor) =
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
