package com.example.polidea.view.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.polidea.R
import com.example.polidea.base.domain.viewing.BaseViewing
import com.example.polidea.base.view.BaseFragment
import com.example.polidea.common.WebClient
import com.example.polidea.view.main.details.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<BaseViewing, DetailsPresenter>(), BaseViewing {

	private val presenter: DetailsPresenter by viewModel()

	override fun providePresenter() = presenter

	//region lifecycle

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? =
		inflater.inflate(R.layout.fragment_details, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setUpWebView()
	}

	//endregion lifecycle

	//region setup

	private fun setUpWebView() {
		val client = WebClient()
		client.setOnProgressChangedListener {
			if (it == 100)
				progressDetails.visibility = View.GONE
			progressDetails.progress = it
		}

		val url = DetailsFragmentArgs.fromBundle(arguments).url

		webViewDetails.webChromeClient = client
		webViewDetails.loadUrl(url)
	}

	//endregion setup

}