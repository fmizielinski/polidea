package com.example.polidea.view.main.search.presenter

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.polidea.base.domain.presenter.BasePresenter
import com.example.polidea.common.runAsyncReturnOnMain
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.GetOrderValuesUseCase
import com.example.polidea.domain.usecase.GetSortValuesUseCase
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SearchPresenter(
	private val searchUseCase: SearchUseCase,
	private val getOrderValuesUseCase: GetOrderValuesUseCase,
	private val getSortValuesUseCase: GetSortValuesUseCase
) : BasePresenter<SearchViewing>() {

	private var questions: PagedList<QuestionDto>? = null
	private var query: String = ""
	private var order: String = "desc"
	private var sort: String = "activity"

	private lateinit var dataSourceFactory: SearchDataSourceFactory

	//region lifecycle

	override fun onFirstBind() {
		dataSourceFactory =
				SearchDataSourceFactory(searchUseCase, compositeDisposable, ::searchError)
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
		present(SearchViewing::displayProgress)
		this.query = query
		dataSourceFactory.search(query, order, sort)
	}

	private fun searchSuccess(questions: PagedList<QuestionDto>) {
		Timber.d("searchSuccess")
		this.questions = questions
		present {
			it.displayQuestions(questions)
			it.hideProgress()
		}
	}

	private fun searchError() {
		present {
			it.displayError()
			it.hideProgress()
		}
	}

	//endregion search

	//region filter

	fun displayFilters() {
		Singles.zip(getOrderValuesUseCase.execute(), getSortValuesUseCase.execute())
			.subscribe(::displayFiltersSuccess, Timber::e)
			.addTo(compositeDisposable)
	}

	private fun displayFiltersSuccess(values: Pair<List<String>, List<String>>) {
		present { it.displayFilterDialog(values.first, values.second, order, sort) }
	}

	fun setOrder(order: String) {
		if (this.order == order)
			return
		this.order = order
		search(refresh = true)
	}

	fun setSort(sort: String) {
		if (this.sort == sort)
			return
		this.sort = sort
		search(refresh = true)
	}

	//endregion filter
}