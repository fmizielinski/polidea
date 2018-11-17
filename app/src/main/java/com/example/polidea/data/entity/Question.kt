package com.example.polidea.data.entity

import com.google.gson.annotations.SerializedName

data class Question(
	val title: String,
	val link: String,
	@SerializedName("answer_count") val answerCount: Int,
	@SerializedName("is_answered") val isAnswered: Boolean,
	val owner: Owner
)