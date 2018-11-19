package com.example.polidea.domain.usecase

import com.example.polidea.common.runAsyncReturnOnMain
import com.example.polidea.domain.datasource.local.LocalDataSource
import io.reactivex.Single

class GetOrderValuesUseCase(private val dataSource: LocalDataSource) {

	fun execute(): Single<List<String>> =
			dataSource.getOrderValues()
				.runAsyncReturnOnMain()
}