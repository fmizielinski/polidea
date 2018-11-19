package com.example.polidea.di

import com.example.polidea.data.local.LocalRepository
import com.example.polidea.data.local.LocalRepositoryImpl
import com.example.polidea.domain.datasource.local.LocalDataSource
import com.example.polidea.domain.datasource.local.LocalDataSourceImpl
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory

object LocalModule {

	val module = module {

		factory<LocalRepositoryImpl>() bind LocalRepository::class

		factory<LocalDataSourceImpl>() bind LocalDataSource::class
	}
}