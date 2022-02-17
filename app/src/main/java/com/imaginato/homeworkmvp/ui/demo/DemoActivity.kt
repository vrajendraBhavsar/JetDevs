package com.imaginato.homeworkmvp.ui.demo

import android.view.LayoutInflater
import com.imaginato.homeworkmvp.databinding.ActivityDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : com.imaginato.homeworkmvp.ui.demo.common.BaseActivity<ActivityDemoBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityDemoBinding.inflate(layoutInflater)

}