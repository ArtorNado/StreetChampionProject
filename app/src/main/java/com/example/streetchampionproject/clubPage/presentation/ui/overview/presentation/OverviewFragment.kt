package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector

class OverviewFragment : Fragment() {

    /*@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewModel: OverviewViewModel? = null*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.overview_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
/*
        Injector.plusOverviewFeatureComponent(12).inject(this)
*/
/*
        initViewModel()
*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

   /* private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(OverviewViewModel::class.java)
        }
        this.viewModel = viewModel
    }*/

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearOverviewFeatureComponent()
    }

}
