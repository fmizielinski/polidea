package com.example.polidea.domain.datasource.local

import io.reactivex.Single

interface LocalDataSource {

	fun getOrderValues(): Single<List<String>>

	fun getSortValues(): Single<List<String>>
}