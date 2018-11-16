package com.example.polidea.view.main.search.presenter

import com.example.polidea.base.domain.viewing.BaseViewing
import com.example.polidea.domain.dto.QuestionDto

interface SearchViewing : BaseViewing {

	fun displayQuestions(questions: List<QuestionDto>)
}