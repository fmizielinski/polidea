package com.example.polidea.view.main.search.filter

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.polidea.R
import kotlinx.android.synthetic.main.dialog_filter.view.*

class FilterDialog : DialogFragment() {

	private var provider: FilterProvider? = null

	companion object {
		private val EXTRA_ORDER = "EXTRA_ORDER"
		private val EXTRA_ORDER_VALUES = "EXTRA_ORDER_VALUES"
		private val EXTRA_SORT = "EXTRA_SORT"
		private val EXTRA_SORT_VALUES = "EXTRA_SORT_VALUES"

		fun newInstance(
			orderValues: List<String>,
			sortValues: List<String>,
			order: String,
			sort: String,
			provider: FilterProvider
		): DialogFragment {
			val bundle = Bundle(1)
			bundle.putStringArrayList(EXTRA_ORDER_VALUES, ArrayList(orderValues))
			bundle.putStringArrayList(EXTRA_SORT_VALUES, ArrayList(sortValues))
			bundle.putString(EXTRA_ORDER, order)
			bundle.putString(EXTRA_SORT, sort)

			val dialog = FilterDialog()
			dialog.arguments = bundle
			dialog.provider = provider

			return dialog
		}
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val orderValues = arguments!!.getStringArrayList(EXTRA_ORDER_VALUES)!!
		val sortValues = arguments!!.getStringArrayList(EXTRA_SORT_VALUES)!!
		val order = arguments!!.getString(EXTRA_ORDER)!!
		val sort = arguments!!.getString(EXTRA_SORT)!!

		val inflater = LayoutInflater.from(context)
		val view = inflater.inflate(R.layout.dialog_filter, null)

		val orderAdapter =
			ArrayAdapter<String>(
				context!!,
				android.R.layout.simple_spinner_dropdown_item,
				orderValues
			)
		view.spinnerFilterOrder.adapter = orderAdapter
		view.spinnerFilterOrder.setSelection(orderValues.indexOf(order))
		view.spinnerFilterOrder.onItemSelectedListener =
				object : AdapterView.OnItemSelectedListener {
					override fun onNothingSelected(parent: AdapterView<*>?) {}

					override fun onItemSelected(
						parent: AdapterView<*>?,
						view: View?,
						position: Int,
						id: Long
					) {
						provider?.onOrderChanged(orderValues[position])
					}
				}

		val sortAdapter =
			ArrayAdapter<String>(
				context!!,
				android.R.layout.simple_spinner_dropdown_item,
				sortValues
			)
		view.spinnerFilterSort.adapter = sortAdapter
		view.spinnerFilterSort.setSelection(sortValues.indexOf(sort))
		view.spinnerFilterSort.onItemSelectedListener =
				object : AdapterView.OnItemSelectedListener {
					override fun onNothingSelected(parent: AdapterView<*>?) {}

					override fun onItemSelected(
						parent: AdapterView<*>?,
						view: View?,
						position: Int,
						id: Long
					) {
						provider?.onSortChanged(sortValues[position])
					}
				}

		return AlertDialog.Builder(context!!)
			.setView(view)
			.setPositiveButton(android.R.string.ok, null)
			.create()
	}
}

interface FilterProvider {

	fun onOrderChanged(order: String)

	fun onSortChanged(sort: String)
}