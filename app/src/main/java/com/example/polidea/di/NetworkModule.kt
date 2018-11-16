package com.example.polidea.di

import com.example.polidea.data.networking.ApiService
import com.example.polidea.domain.datasource.ApiDataSource
import com.example.polidea.domain.datasource.ApiDataSourceImpl
import com.example.polidea.domain.mapper.QuestionMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

	val module = module {

		factory { RxJava2CallAdapterFactory.create() } bind CallAdapter.Factory::class

		factory { GsonConverterFactory.create() } bind Converter.Factory::class

		factory {
			HttpLoggingInterceptor()
				.setLevel(HttpLoggingInterceptor.Level.BODY)
		} bind Interceptor::class

		single {
			OkHttpClient.Builder()
				.addInterceptor(get())
				.build()
		}

		single {
			Retrofit.Builder()
				.baseUrl("http://api.stackexchange.com/")
				.client(get())
				.addCallAdapterFactory(get())
				.addConverterFactory(get())
				.build()
		}

		factory { get<Retrofit>().create(ApiService::class.java) }

		factory<ApiDataSourceImpl>() bind ApiDataSource::class

		factory<QuestionMapper>()
	}
}