package com.example.polidea.view.main.search.presenter

import com.example.polidea.base.domain.presenter.BasePresenter
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SearchPresenter(private val searchUseCase: SearchUseCase) : BasePresenter<SearchViewing>() {

	//region lifecycle

	override fun onFirstBind() {
		search("android")
	}

	override fun onViewRestoreState() {
		search("android")
	}

	//endregion lifecycle

	//region search

	fun search(query: String) {
		searchUseCase.execute(query)
			.subscribe(::searchSuccess, Timber::e)
			.addTo(compositeDisposable)
	}

	private fun searchSuccess(questions: List<QuestionDto>) {
		Timber.d("searchSuccess")
		present { it.displayQuestions(questions) }
	}

	//endregion search
}