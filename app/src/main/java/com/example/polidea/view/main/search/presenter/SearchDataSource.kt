package com.example.polidea.view.main.search.presenter

import androidx.paging.PageKeyedDataSource
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SearchDataSource(
	private val searchUseCase: SearchUseCase,
	private val compositeDisposable: CompositeDisposable,
	private var query: String,
	private var order: String,
	private var sort: String,
	private val onErrorListener: () -> Unit
) :
	PageKeyedDataSource<Int, QuestionDto>() {

	private var searchAction: (() -> Unit)? = null

	override fun loadInitial(
		params: LoadInitialParams<Int>,
		callback: LoadInitialCallback<Int, QuestionDto>
	) {
		searchAction = {
			loadInitial(params, callback)
		}
		if (query.isEmpty())
			return
		searchUseCase.execute(1, params.requestedLoadSize, query, order, sort)
			.subscribe({
				callback.onResult(it, 1, 2)
			}, {
				Timber.e(it)
				onErrorListener.invoke()
			})
			.addTo(compositeDisposable)
	}

	override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QuestionDto>) {
		if (query.isEmpty())
			return
		searchUseCase.execute(params.key, params.requestedLoadSize, query, order, sort)
			.subscribe({
				callback.onResult(it, params.key + 1)
			}, {
				Timber.e(it)
				onErrorListener.invoke()
			})
			.addTo(compositeDisposable)

	}

	override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, QuestionDto>) {}
}