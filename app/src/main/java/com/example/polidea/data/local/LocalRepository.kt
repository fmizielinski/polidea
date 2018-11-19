package com.example.polidea.data.local

import io.reactivex.Single

interface LocalRepository {

	fun getOrderValues(): Single<List<String>>

	fun getSortValues(): Single<List<String>>
}