package com.example.polidea.view.main.search.presenter

import androidx.paging.DataSource
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.disposables.CompositeDisposable

class SearchDataSourceFactory(
	private val searchUseCase: SearchUseCase,
	private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, QuestionDto>() {

	private var query: String = ""

	private var dataSource: SearchDataSource? = null

	override fun create(): DataSource<Int, QuestionDto> {
		dataSource = SearchDataSource(searchUseCase, compositeDisposable, query)
		return dataSource!!
	}

	fun search(query: String) {
		this.query = query
		dataSource?.invalidate()
	}
}