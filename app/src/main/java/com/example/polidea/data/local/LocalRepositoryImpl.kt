package com.example.polidea.data.local

import io.reactivex.Single

class LocalRepositoryImpl : LocalRepository {

	override fun getOrderValues(): Single<List<String>> =
		Single.fromCallable {
			listOf(
				"desc",
				"asc"
			)
		}

	override fun getSortValues(): Single<List<String>> =
		Single.fromCallable {
			listOf(
				"activity",
				"votes",
				"creation",
				"relevance"
			)
		}
}