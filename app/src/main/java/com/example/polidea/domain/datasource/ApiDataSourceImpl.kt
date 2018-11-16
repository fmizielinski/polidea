package com.example.polidea.domain.datasource

import com.example.polidea.data.networking.ApiService
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.mapper.QuestionMapper
import io.reactivex.Single

class ApiDataSourceImpl(
	private val apiService: ApiService,
	private val questionMapper: QuestionMapper
) : ApiDataSource {

	override fun search(query: String): Single<List<QuestionDto>> =
		apiService.search(query = query)
			.map { questionMapper.map(it.items) }
}