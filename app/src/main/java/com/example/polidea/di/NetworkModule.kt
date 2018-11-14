package com.example.polidea.di

import com.example.polidea.data.networking.ApiService
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

	val module = module {

		factory { RxJava2CallAdapterFactory.create() } bind CallAdapter.Factory::class

		factory { GsonConverterFactory.create() } bind Converter.Factory::class

		single {
			Retrofit.Builder()
				.baseUrl("http://api.stackexchange.com/")
				.addCallAdapterFactory(get())
				.addConverterFactory(get())
				.build()
		}

		factory { get<Retrofit>().create(ApiService::class.java) }
	}
}