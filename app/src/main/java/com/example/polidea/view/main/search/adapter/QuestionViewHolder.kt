package com.example.polidea.view.main.search.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.polidea.R
import com.example.polidea.domain.dto.QuestionDto
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	fun bind(question: QuestionDto) {
		itemView.textQuestionTitle.text = question.title
		itemView.textQuestionOwner.text = question.owner
		itemView.textQuestionAnswers.text = itemView.context.resources.getQuantityText(
			R.plurals.question_answer,
			question.answerCount
		)
		itemView.textQuestionAnswersCount.text = question.answerCount.toString()

		if (question.isAnswered) {
			itemView.layoutQuestionAnswer.setBackgroundResource(R.drawable.background_answer_count_answered)
			val color = ContextCompat.getColor(itemView.context, R.color.colorAnswered)
			itemView.textQuestionAnswers.setTextColor(color)
			itemView.textQuestionAnswersCount.setTextColor(color)
		} else {
			itemView.layoutQuestionAnswer.setBackgroundResource(R.drawable.background_answer_count)
			val color = ContextCompat.getColor(itemView.context, android.R.color.darker_gray)
			itemView.textQuestionAnswers.setTextColor(color)
			itemView.textQuestionAnswersCount.setTextColor(color)
		}
	}
}