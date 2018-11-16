package com.example.polidea.common

import io.reactivex.Single

fun <T> Single<T>.runAsyncReturnOnMain(): Single<T> =
	this.subscribeOn(io.reactivex.schedulers.Schedulers.io())
		.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())