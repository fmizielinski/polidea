package com.example.polidea.data.networking

import com.example.polidea.data.entity.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("/2.2/search?site=stackoverflow")
	fun search(@Query("order") order: String = "desc",
			   @Query("sort") sort: String = "activity",
			   @Query("intitle") query: String): Single<SearchResponse>
}