package com.example.polidea.domain.dto

data class QuestionDto(
	val title: String,
	val answerCount: Int,
	val isAnswered: Boolean,
	val owner: String
)