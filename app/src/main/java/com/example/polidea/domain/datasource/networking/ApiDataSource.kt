package com.example.polidea.domain.datasource.networking

import com.example.polidea.domain.dto.QuestionDto
import io.reactivex.Single

interface ApiDataSource {

	fun search(
		page: Int,
		pageSize: Int,
		query: String,
		order: String,
		sort: String
	): Single<List<QuestionDto>>
}