package com.example.polidea.view.main.search.presenter

import com.example.polidea.base.domain.presenter.BasePresenter
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SearchPresenter(private val searchUseCase: SearchUseCase) : BasePresenter<SearchViewing>() {

	private var questions: List<QuestionDto> = emptyList()
	private var query: String = ""

	//region lifecycle

	override fun onFirstBind() {}

	override fun onViewRestoreState() {
		present { it.displayQuestions(questions) }
	}

	//endregion lifecycle

	//region search

	fun search(query: String = this.query, refresh: Boolean = false) {
		if (this.query == query && !refresh)
			return
		this.query = query
		searchUseCase.execute(query)
			.subscribe(::searchSuccess, Timber::e)
			.addTo(compositeDisposable)
	}

	private fun searchSuccess(questions: List<QuestionDto>) {
		Timber.d("searchSuccess")
		this.questions = questions
		present { it.displayQuestions(questions) }
	}

	//endregion search
}