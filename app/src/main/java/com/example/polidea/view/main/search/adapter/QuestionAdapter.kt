package com.example.polidea.view.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polidea.R
import com.example.polidea.domain.dto.QuestionDto

class QuestionAdapter(private val items: List<QuestionDto>) :
	RecyclerView.Adapter<QuestionViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val view = inflater.inflate(R.layout.item_question, parent, false)
		return QuestionViewHolder(view)
	}

	override fun getItemCount() = items.size

	override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
		val item = items[position]
		holder.bind(item)
	}
}