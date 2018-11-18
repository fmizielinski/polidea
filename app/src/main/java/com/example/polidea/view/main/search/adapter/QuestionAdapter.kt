package com.example.polidea.view.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.polidea.R
import com.example.polidea.domain.dto.QuestionDto
import io.reactivex.subjects.PublishSubject

class QuestionAdapter : PagedListAdapter<QuestionDto, QuestionViewHolder>(DIFF_CALLBACK) {

	val onItemSelectedListener: PublishSubject<String> = PublishSubject.create()

	companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<QuestionDto>() {
			override fun areItemsTheSame(oldItem: QuestionDto, newItem: QuestionDto): Boolean =
				oldItem.id == newItem.id

			override fun areContentsTheSame(oldItem: QuestionDto, newItem: QuestionDto): Boolean =
				oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val view = inflater.inflate(R.layout.item_question, parent, false)
		return QuestionViewHolder(view)
	}

	override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
		getItem(position)?.let {item ->
			holder.bind(item)
			holder.itemView.setOnClickListener { onItemSelectedListener.onNext(item.link) }
		}
	}
}