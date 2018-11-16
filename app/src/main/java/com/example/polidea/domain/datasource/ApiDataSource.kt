package com.example.polidea.domain.datasource

import com.example.polidea.domain.dto.QuestionDto
import io.reactivex.Single

interface ApiDataSource {

	fun search(query: String): Single<List<QuestionDto>>
}