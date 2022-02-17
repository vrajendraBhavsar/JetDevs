package com.imaginato.homeworkmvp.ui.demo.common

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.imaginato.homeworkmvp.R
import com.imaginato.homeworkmvp.common.enums.NetworkStatus
import com.imaginato.homeworkmvp.common.extensions.safeObserve
import kotlinx.coroutines.launch

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var noInternetDialog: Dialog? = null
    private val viewModel: BaseViewModel by viewModels()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObserver()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initObserver() {
    }

    private fun dismissNoInternetDialog() {
        if (noInternetDialog != null) {
            if (noInternetDialog!!.isShowing) {
                noInternetDialog?.dismiss()
            }
            noInternetDialog = null
        }
    }

    @CallSuper
    protected open fun initViews() {}

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        dismissNoInternetDialog()
    }
}