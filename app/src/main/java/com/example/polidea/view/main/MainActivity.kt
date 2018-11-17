package com.example.polidea.view.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.polidea.R
import com.example.polidea.base.domain.viewing.BaseViewing
import com.example.polidea.base.view.BaseActivity
import com.example.polidea.view.main.presenter.MainPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<BaseViewing, MainPresenter>(), BaseViewing {

	private val presenter: MainPresenter by viewModel()

	override fun providePresenter() = presenter

	//region lifecycle

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
//		findNavController(R.id.fragmentMain)
	}

	//endregion lifecycle
}
