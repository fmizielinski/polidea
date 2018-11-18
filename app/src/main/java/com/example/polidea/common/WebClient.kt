package com.example.polidea.common

import android.webkit.WebChromeClient
import android.webkit.WebView

class WebClient : WebChromeClient() {

	private var onProgressChangedListener: ((Int) -> Unit)? = null

	override fun onProgressChanged(view: WebView?, newProgress: Int) {
		onProgressChangedListener?.invoke(newProgress)
		super.onProgressChanged(view, newProgress)
	}

	fun setOnProgressChangedListener(listener: (Int) -> Unit) {
		this.onProgressChangedListener = listener
	}
}