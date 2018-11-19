package com.example.polidea.domain.datasource.networking

import com.example.polidea.data.networking.ApiService
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.mapper.QuestionMapper
import io.reactivex.Single

class ApiDataSourceImpl(
	private val apiService: ApiService,
	private val questionMapper: QuestionMapper
) : ApiDataSource {

	override fun search(
		page: Int,
		pageSize: Int,
		query: String,
		order: String,
		sort: String
	): Single<List<QuestionDto>> =
		apiService.search(page, pageSize, query, order, sort)
			.map { questionMapper.map(it.items) }
}