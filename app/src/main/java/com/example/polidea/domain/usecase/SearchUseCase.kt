package com.example.polidea.domain.usecase

import com.example.polidea.common.runAsyncReturnOnMain
import com.example.polidea.domain.datasource.ApiDataSource
import com.example.polidea.domain.dto.QuestionDto
import io.reactivex.Single

class SearchUseCase(private val dataSource: ApiDataSource) {

	fun execute(page: Int, pageSize: Int, query: String): Single<List<QuestionDto>> =
		dataSource.search(page, pageSize, query)
			.runAsyncReturnOnMain()
}