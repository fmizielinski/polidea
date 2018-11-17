package com.example.polidea.domain.mapper

import com.example.polidea.data.entity.Question
import com.example.polidea.domain.dto.QuestionDto

class QuestionMapper {

	private fun map(question: Question) =
		QuestionDto(
			question.title,
			question.answerCount,
			question.isAnswered,
			question.owner.displayName
		)

	fun map(questions: List<Question>): List<QuestionDto> =
		questions.map(::map)
}