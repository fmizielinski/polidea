package com.example.polidea.view.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polidea.R
import com.example.polidea.base.view.BaseFragment
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.view.main.search.adapter.QuestionAdapter
import com.example.polidea.view.main.search.presenter.SearchPresenter
import com.example.polidea.view.main.search.presenter.SearchViewing
import kotlinx.android.synthetic.main.fragment_search.*
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

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setUpRecycler()
		setUpSearchBar()
		setUpRefresh()
		setUpError()
	}

	//endregion lifecycle

	//region setup

	private fun setUpRecycler() {
		val layoutManager = LinearLayoutManager(context)
		val adapter = QuestionAdapter()
		adapter.onItemSelectedListener
			.subscribe(::openDetails)

		recyclerSearch.adapter = adapter
		recyclerSearch.layoutManager = layoutManager
	}

	private fun setUpSearchBar() {
		searchBarSearch.setOnSearchQueryChangedListener { presenter.search(it) }
	}

	private fun setUpRefresh() {
		swipeLayoutSearch.setOnRefreshListener { presenter.search(refresh = true) }
	}

	private fun setUpError() {
		imageSearch.setOnClickListener { presenter.search(refresh = true) }
		textSearch.setOnClickListener { presenter.search(refresh = true) }
	}

	//endregion setup

	//region viewing

	override fun displayQuestions(questions: PagedList<QuestionDto>) {
		groupSearch.visibility = View.GONE
		recyclerSearch.visibility = View.VISIBLE
		(recyclerSearch.adapter as? QuestionAdapter)?.submitList(questions)
		swipeLayoutSearch.isRefreshing = false
	}

	override fun displayError() {
		groupSearch.visibility = View.VISIBLE
		recyclerSearch.visibility = View.GONE
	}

	//endregion viewing

	private fun openDetails(url: String) {
		val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(url)
		findNavController().navigate(action)
	}

}