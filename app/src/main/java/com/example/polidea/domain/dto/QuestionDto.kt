package com.example.polidea.domain.dto

data class QuestionDto(
	val id: Long,
	val title: String,
	val answerCount: Int,
	val isAnswered: Boolean,
	val owner: String,
	val ownerImageUrl: String?,
	val link: String
)