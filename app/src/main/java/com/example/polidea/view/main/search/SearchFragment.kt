package com.example.polidea.view.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.polidea.R
import com.example.polidea.base.view.BaseFragment
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.view.main.search.presenter.SearchPresenter
import com.example.polidea.view.main.search.presenter.SearchViewing
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchViewing, SearchPresenter>(), SearchViewing {

	private val presenter: SearchPresenter by viewModel()

	override fun providePresenter() = presenter

	//region lifecycle

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? =
		inflater.inflate(R.layout.fragment_search, container, false)

	//endregion lifecycle

	//region viewing

	override fun displayQuestions(questions: List<QuestionDto>) {
	}

	//endregion viewing

}