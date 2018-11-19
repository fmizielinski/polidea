package com.example.polidea.domain.usecase

import com.example.polidea.common.runAsyncReturnOnMain
import com.example.polidea.domain.datasource.networking.ApiDataSource
import com.example.polidea.domain.dto.QuestionDto
import io.reactivex.Single

class SearchUseCase(private val dataSource: ApiDataSource) {

	fun execute(
		page: Int,
		pageSize: Int,
		query: String,
		order: String,
		sort: String
	): Single<List<QuestionDto>> =
		dataSource.search(page, pageSize, query, order, sort)
			.runAsyncReturnOnMain()
}