package com.example.polidea.di

import com.example.polidea.domain.usecase.GetOrderValuesUseCase
import com.example.polidea.domain.usecase.GetSortValuesUseCase
import com.example.polidea.domain.usecase.SearchUseCase
import com.example.polidea.view.main.details.presenter.DetailsPresenter
import com.example.polidea.view.main.presenter.MainPresenter
import com.example.polidea.view.main.search.presenter.SearchPresenter
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory

object DomainModule {
	val module = module {

		factory<SearchUseCase>()
		factory<GetOrderValuesUseCase>()
		factory<GetSortValuesUseCase>()

		factory<SearchPresenter>()
		factory<DetailsPresenter>()
		factory<MainPresenter>()
	}
}