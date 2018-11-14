package com.example.polidea.data.entity

import com.google.gson.annotations.SerializedName

data class Question(
	val title: String,
	val link: String,
	@SerializedName("answer_count") val answerCount: Int,
	val owner: Owner
)