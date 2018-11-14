package com.example.polidea

import android.app.Application
import com.example.polidea.di.NetworkModule
import org.koin.android.ext.android.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin(this, listOf(NetworkModule.module))
	}
}