package com.example.polidea.view.main.search.presenter

import androidx.paging.PagedList
import com.example.polidea.base.domain.viewing.BaseViewing
import com.example.polidea.domain.dto.QuestionDto

interface SearchViewing : BaseViewing {

	fun displayQuestions(questions: PagedList<QuestionDto>)

	fun displayError()

	fun displayProgress()

	fun hideProgress()
}