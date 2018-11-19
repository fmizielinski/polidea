package com.example.polidea.domain.datasource.local

import com.example.polidea.data.local.LocalRepository
import io.reactivex.Single

class LocalDataSourceImpl(private val localRepository: LocalRepository) : LocalDataSource {

	override fun getOrderValues(): Single<List<String>> = localRepository.getOrderValues()

	override fun getSortValues(): Single<List<String>> = localRepository.getSortValues()
}