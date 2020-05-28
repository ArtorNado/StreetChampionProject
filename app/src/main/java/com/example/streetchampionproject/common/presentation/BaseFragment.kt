package com.example.streetchampionproject.common.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    protected open lateinit var viewModel: T

    private val observables = mutableListOf<LiveData<*>>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribe(viewModel)
        initClickListeners()
        observe(viewModel.errorLiveData, Observer {
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                getString(it),
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    override fun onDestroyView() {
        Log.e("onDestroyView", "onDestroyView")
        observables.forEach { it.removeObservers(this) }
        super.onDestroyView()
    }

    @Suppress("unchecked_cast")
    protected fun <V : Any?> observe(source: LiveData<V>, observer: Observer<V>) {
        source.observe(viewLifecycleOwner, observer as Observer<in Any?>)
        observables.add(source)
    }

    abstract fun inject()

    abstract fun initClickListeners()

    abstract fun subscribe(viewModel: T)
}
