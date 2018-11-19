package com.example.polidea.common.view

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.example.polidea.R
import com.example.polidea.common.SimpleTextWatcher
import kotlinx.android.synthetic.main.view_search_bar.view.*

class SearchBar @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	private var onSearchQueryChangedListener: ((String) -> Unit)? = null
	private var onFilterClickedListener: (() -> Unit)? = null

	init {
		View.inflate(context, R.layout.view_search_bar, this)

		editTextSearch.addTextChangedListener(object : SimpleTextWatcher() {
			override fun afterTextChanged(s: Editable?) {
				if (s == null)
					return

				val string = s.toString()
				if (string.length > 3)
					onSearchQueryChangedListener?.invoke(string)
			}
		})
		editTextSearch.setOnEditorActionListener { view, actionId, _ ->
			if (actionId == EditorInfo.IME_ACTION_SEARCH && view.text.isNotEmpty())
				onSearchQueryChangedListener?.invoke(view.text.toString())
			true
		}
		buttonSearchConfirm.setOnClickListener {
			if (editTextSearch.text?.isNotEmpty() == true)
				onSearchQueryChangedListener?.invoke(editTextSearch.text.toString())
		}
		buttonSearchFilter.setOnClickListener { onFilterClickedListener?.invoke() }

	}

	fun setOnSearchQueryChangedListener(listener: (String) -> Unit) {
		onSearchQueryChangedListener = listener
	}

	fun setOnFilterClickedListener(listener: () -> Unit) {
		onFilterClickedListener = listener
	}
}