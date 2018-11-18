package com.example.polidea.view.main.search.presenter

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.polidea.base.domain.presenter.BasePresenter
import com.example.polidea.common.runAsyncReturnOnMain
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SearchPresenter(private val searchUseCase: SearchUseCase) : BasePresenter<SearchViewing>() {

	private var questions: PagedList<QuestionDto>? = null
	private var query: String = ""

	private lateinit var dataSourceFactory: SearchDataSourceFactory

	//region lifecycle

	override fun onFirstBind() {
		dataSourceFactory = SearchDataSourceFactory(searchUseCase, compositeDisposable, ::searchError)
		RxPagedListBuilder<Int, QuestionDto>(dataSourceFactory, 30)
			.buildObservable()
			.runAsyncReturnOnMain()
			.subscribe(::searchSuccess, Timber::e)
			.addTo(compositeDisposable)
	}

	override fun onViewRestoreState() {
		if (questions != null)
			present { it.displayQuestions(questions!!) }
	}

	//endregion lifecycle

	//region search

	fun search(query: String = this.query, refresh: Boolean = false) {
		if (this.query == query && !refresh)
			return
		this.query = query
		dataSourceFactory.search(query)
	}

	private fun searchSuccess(questions: PagedList<QuestionDto>) {
		Timber.d("searchSuccess")
		this.questions = questions
		present { it.displayQuestions(questions) }
	}

	private fun searchError() {
		present(SearchViewing::displayError)
	}

	//endregion search
}