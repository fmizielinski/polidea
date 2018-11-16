package com.example.polidea.domain.usecase

import com.example.polidea.common.runAsyncReturnOnMain
import com.example.polidea.domain.datasource.ApiDataSource
import com.example.polidea.domain.dto.QuestionDto
import io.reactivex.Single

class SearchUseCase(private val dataSource: ApiDataSource) {

	fun execute(query: String): Single<List<QuestionDto>> =
		dataSource.search(query)
			.runAsyncReturnOnMain()
}