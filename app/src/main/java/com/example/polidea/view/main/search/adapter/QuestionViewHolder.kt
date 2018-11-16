package com.example.polidea.view.main.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.polidea.R
import com.example.polidea.domain.dto.QuestionDto
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	fun bind(question: QuestionDto) {
		itemView.textQuestionTitle.text = question.title
		itemView.textQuestionOwner.text = question.owner

		val answers = String.format(
			itemView.context.resources.getQuantityText(
				R.plurals.question_answer,
				question.answerCount
			).toString(),
			question.answerCount
		)
		itemView.textQuestionAnswers.text = answers
	}
}