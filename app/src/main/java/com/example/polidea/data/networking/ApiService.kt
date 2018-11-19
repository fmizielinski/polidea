package com.example.polidea.data.networking

import com.example.polidea.data.entity.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

	@GET("/2.2/search?site=stackoverflow")
	@Headers(
		"Accept: application/json; charset=utf-8"
	)
	fun search(
		@Query("page") page: Int,
		@Query("pagesize") pageSize: Int,
		@Query("intitle") query: String,
		@Query("order") order: String,
		@Query("sort") sort: String
	): Single<SearchResponse>
}